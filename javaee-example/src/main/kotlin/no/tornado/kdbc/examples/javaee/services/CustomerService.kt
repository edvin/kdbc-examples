package no.tornado.kdbc.examples.javaee.services

import no.tornado.kdbc.examples.javaee.models.Customer
import no.tornado.kdbc.examples.javaee.models.SelectCustomer
import no.tornado.kdbc.examples.javaee.models.UpdateCustomer
import javax.ejb.Stateless

@Stateless
open class CustomerService {
    open fun list() = SelectCustomer().list()
    open fun byId(id: Int) = SelectCustomer().byId(id)
    open fun search(term: String) = SelectCustomer().search(term)
    open fun update(customer: Customer) = UpdateCustomer(customer).execute()
}
