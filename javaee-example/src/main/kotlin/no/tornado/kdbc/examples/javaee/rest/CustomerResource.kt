package no.tornado.kdbc.examples.javaee.rest

import no.tornado.kdbc.examples.javaee.services.CustomerService
import javax.inject.Inject
import javax.json.Json
import javax.json.JsonArray
import javax.json.JsonObject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("customers")
@Produces(APPLICATION_JSON)
open class CustomerResource {
    @Inject lateinit private var customerService: CustomerService

    @GET
    @Path("{id}")
    open fun getCustomer(@PathParam("id") id: Int) = customerService.byId(id).toJSON()

    @GET
    open fun listCustomers(): JsonArray {
        val customers = customerService.list()

        val json = Json.createArrayBuilder().apply {
            customers.forEach { add(it.toJSON()) }
        }

        return json.build()
    }

    @GET
    @Path("search/{term}")
    open fun searchCustomers(@PathParam("term") term: String): JsonArray {
        val customers = customerService.search(term)

        val json = Json.createArrayBuilder().apply {
            customers.forEach { add(it.toJSON()) }
        }

        return json.build()
    }

    @GET
    @Path("{id}/updateName/{name}")
    open fun updateName(@PathParam("id") id: Int, @PathParam("name") name: String): JsonObject {
        val customer = customerService.byId(id)
        customer.name = name
        customerService.update(customer)
        return customer.toJSON()
    }

}
