package no.tornado.kdbc.examples.javaee.services

import kdbc.db
import no.tornado.kdbc.examples.javaee.models.Customer
import no.tornado.kdbc.examples.javaee.models.SelectCustomer
import no.tornado.kdbc.examples.javaee.models.UpdateCustomer
import javax.annotation.Resource
import javax.ejb.Stateless
import javax.sql.DataSource

@Stateless
open class CustomerService {
    @Resource lateinit private var ds: DataSource

    // Convenience function to instantiate the SelectCustomer query and assign a database connection to it.
    // This could probably be improved in a declarative way, suggestions are welcome.
    // Granted, it is very explicit and easy to reason about so it feels like "The Kotlin Way(TM)"
    private fun select() = SelectCustomer().db(ds)

    open fun list() = select().list()
    open fun byId(id: Int) = select().byId(id)
    open fun search(term: String) = select().search(term)
    open fun update(customer: Customer) = UpdateCustomer(customer).execute(ds)
}
