# Java EE Example usage for KDBC

This demo showcases how to create basic queries and how to integrate [KDBC](https://github.com/edvin/kdbc) with a JavaEE Container.

It uses the default DataSource provided by the container. The [CreateExampleData](src/main/kotlin/no/tornado/kdbc/examples/javaee/CreateExampleData.kt)
class will create some tables and put some sample data into it when the container starts.

The [Customer class](src/main/kotlin/no/tornado/kdbc/examples/javaee/models/Customer.kt)
contains the `Customer` model object, the `CustomerTable` column definition
as well as all the SQL queries for Customer.

These queries are used in the [CustomerService](src/main/kotlin/no/tornado/kdbc/examples/javaee/models/Customer.kt)
where they are given a database connection before they are executed.

There is also a [REST API](src/main/kotlin/no/tornado/kdbc/examples/javaee/rest/CustomerResource.kt)
that fetches data via the `CustomerService`. By default, this api is available at `http://localhost:8080/javaee-example-1.0.0/api/customers` if you deploy 
using the default container setup. These samples were tested with [Wildfly 10](http://wildfly.org/).

* GET [api/customers](http://localhost:8080/javaee-example-1.0.0/api/customers) lists all customers 
* GET [api/customers/1](http://localhost:8080/javaee-example-1.0.0/api/customers/1) lists customer 1
* GET [api/customers/search/ill](http://localhost:8080/javaee-example-1.0.0/api/customers/search/ill) will find Customer 'Jill'
* GET [api/customers/1/updateName/Johnnie](http://localhost:8080/javaee-example-1.0.0/api/customers/1/updateName/Johnnie) will give Customer 'John' a new name

## Note on pre-release

Since KDBC is not released yet, you need to [check it out](https://github.com/edvin/kdbc) and do `./gradlew install` locally before trying this out.