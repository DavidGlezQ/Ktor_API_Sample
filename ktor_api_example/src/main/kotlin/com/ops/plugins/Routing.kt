package com.ops.plugins

import com.ops.routes.userRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World1, Test Ktor!")
        }
        userRouting()
    }
}
