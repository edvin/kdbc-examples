package no.tornado.kdbc.examples.javaee.models

import kdbc.Query
import kdbc.Table
import kdbc.Update
import java.sql.ResultSet
import javax.json.Json
import javax.json.JsonObject

/**
 * The customer class has some fields and two constructors.
 *
 * The primary constructor takes and id and a name.
 * The second constructor takes a CustomerTable object,
 * and extracts the id and name values from that before
 * calling the primary constructor.
 *
 * The populated CustomerTable is returned from the
 * SelectCustomer query.
 *
 * The toJSON() function and the JsonObject constructor is used to marshal
 * JSON data for easier consumption in the REST Customer resource.
 */
class Customer(var id: Int, var name: String) {
    constructor(t: CustomerTable) : this(t.id(), t.name())
    constructor(json: JsonObject) : this(json.getInt("id"), json.getString("name"))

    fun toJSON() = Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .build()

}

/**
 * The customer table knows the name of the database table,
 * and what columns are available.
 *
 * It also knows how to extract values from the result set,
 * given the fully qualified alias the field gets in a query.
 *
 * The column { } lambda is reponsible for this extraction. It
 * operates on a ResultSet instance and `it` is the column alias.
 */
class CustomerTable : Table("customer") {
    val id by column { getInt(it) }
    val name by column { getString(it) }
}


/**
 * Below are all the queries for Customer. These queries
 * are instantiated in the CustomerService class when needed.
 *
 * One Query instance is only used for a single query.
 */
class SelectCustomer : Query<Customer>() {
    val c = CustomerTable()

    init {
        SELECT(c.columns)
        FROM(c)
    }

    fun byId(id: Int): Customer = let {
        WHERE { c.id `=` id }
        first()
    }

    fun search(term: String): List<Customer> = let {
        WHERE { UPPER(c.name) LIKE UPPER("%$term%") }
        list()
    }

    override fun TO(rs: ResultSet) = Customer(c)
}

class UpdateCustomer(customer: Customer) : Update() {
    val c = CustomerTable()

    init {
        UPDATE(c) {
            c.name `=` customer.name
        }
        WHERE {
            c.id `=` customer.id
        }
    }
}