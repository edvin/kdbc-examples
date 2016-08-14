package no.tornado.kdbc.examples.javaee

import kdbc.KDBC
import kdbc.execute
import javax.annotation.PostConstruct
import javax.annotation.Resource
import javax.ejb.Singleton
import javax.ejb.Startup
import javax.sql.DataSource

/**
 * This class configures a default data source for Queries.
 *
 * Not that the DataSourceProvider can return different data sources
 * based on the query provided to it.
 *
 * Alternatively you can supply a datasource on a per-query basis via the Query.db() function.
 *
 * This class also provides some default data to work with for the demo.
 *
 */
@Startup
@Singleton
open class KDBCApplication {
    @Resource lateinit private var dataSource: DataSource

    @PostConstruct
    open fun init() {
        KDBC.setConnectionFactory { dataSource.connection }

        with(dataSource) {
            execute("DROP TABLE IF EXISTS customer")
            execute("CREATE TABLE customer (id integer not null primary key auto_increment, name text)")
            execute("INSERT INTO customer (name) VALUES ('John')")
            execute("INSERT INTO customer (name) VALUES ('Jill')")
        }
    }
}
