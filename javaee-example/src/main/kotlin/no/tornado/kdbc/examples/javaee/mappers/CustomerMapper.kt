package no.tornado.kdbc.examples.javaee.mappers

import kdbc.Query
import kdbc.Update
import no.tornado.kdbc.examples.javaee.models.Customer
import no.tornado.kdbc.examples.javaee.models.CustomerTable
import java.sql.ResultSet

/**
 * This mapper contains all the queries for Customer. These queries
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
        WHERE { c.id EQ id }
        first()
    }

    fun search(term: String): List<Customer> = let {
        WHERE { UPPER(c.name) LIKE UPPER("%$term%") }
        list()
    }

    override fun map(rs: ResultSet) = Customer(c)
}

class UpdateCustomer(customer: Customer) : Update() {
    val c = CustomerTable()

    init {
        UPDATE(c) {
            c.name TO customer.name
        }
        WHERE {
            c.id EQ customer.id
        }
    }
}