package com.example

import com.example.data.user.MongoUserDataSource
import com.example.data.user.User
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val mongoPw = System.getenv("MONGO_PW")
    val dbName = "ktor-auth"

    KMongo.createClient(
        connectionString = "mongodb+srv://arsham:$mongoPw@cluster0.jyind7f.mongodb.net/?retryWrites=true&w=majority"
    ) .coroutine
        .getDatabase(dbName)


//    val userDataSource = MongoUserDataSource(db)
//
//    GlobalScope.launch {
//        val user = User(
//            username = "test",
//            password = "test-password",
//            salt = "salt"
//        )
//        userDataSource.insertUser(user)
//    }

    configureMonitoring()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
