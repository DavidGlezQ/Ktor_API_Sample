package com.ops

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.ops.plugins.*

fun main() {
    //Configuracion del servidor como puerto, url, etc.
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
            .start(wait = true)
}

fun Application.module() {
    /* Configuiracion de configureSerialization(), esta clase es para activar la configuracion para json y dar soporte
    * con ese tipo de serializacion */
    configureSerialization()
    /* Las respuestas que hara el servidor al momento de hacer alguna llamada a la url, esta clase se encarga de manejar
    * cada una de las peticiones a nuestro server */
    configureRouting()
}

/* Notas:
* - La configuracion del gradle se quedo por defecto, no se agrego ninguna libreria
* - La configuracion del proyecto solo fue el plugin de serializable y routing
* - Toda la configuracion en la creacion del proyecto fue practicamente la que viene por defectp
* - Para correr el servidor solo hice un cambio en la ip, por defecto viene en 0.0.0.0, lo cambie por 127.0.0.1
* - Se corre el Application.kt para levantar el servidor local y se hace la prueba en un navegador
* Notas para este ejemplo basico de un crud, API
* - */
