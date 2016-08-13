package no.tornado.kdbc.examples.javaee


import kdbc.execute
import javax.annotation.PostConstruct
import javax.annotation.Resource
import javax.ejb.Singleton
import javax.ejb.Startup
import javax.sql.DataSource

/**
 * This class is only used to bootstrap some data and has nothing to do with
 * how you would configure KDBC in a JavaEE environment.
 */
@Startup
@Singleton
open class CreateExampleData {
    @Resource lateinit private var dataSource: DataSource

    @PostConstruct
    open fun init() {
        with(dataSource) {
            execute("DROP TABLE IF EXISTS customer")
            execute("CREATE TABLE customer (id integer not null primary key auto_increment, name text)")
            execute("INSERT INTO customer (name) VALUES ('John')")
            execute("INSERT INTO customer (name) VALUES ('Jill')")
        }
    }
}
