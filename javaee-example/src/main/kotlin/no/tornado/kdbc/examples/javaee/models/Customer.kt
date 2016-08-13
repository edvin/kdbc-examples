package no.tornado.kdbc.examples.javaee.models

import kdbc.Table
import javax.json.Json

class Customer(var id: Int, var name: String) {
    constructor(t: CustomerTable) : this(t.id(), t.name())

    fun toJSON() = Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .build()
}

class CustomerTable : Table("customer") {
    val id by column { getInt(it) }
    val name by column { getString(it) }
}

