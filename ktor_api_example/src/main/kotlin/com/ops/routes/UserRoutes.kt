package com.ops.routes

import com.ops.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val users = mutableListOf(
    User(1, "David", 24, "dagq117@gmail.com"),
    User(2, "Alejandro", 23, "david.gonzalez@opside.mx")
)

fun Route.userRouting() {
    //Esta seria la ruta para acceder, es decir http://127.0.0.1:8080/users
    route("/users") {
        //Aqui se haria la llamada a la base de datos, en este caso lo hacemos local con un array de los usuarios
        //Se crea el metodo get donde dentro podemos llamar al call.respond convirtiendo la lista en un objeto json
        get {
            if (users.isNotEmpty()) {
                call.respond(users)
            } else {
                //Devolvemos la llamada con sus status, en este caso OK
                call.respondText("There aren't users", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            //Pasar por parametro el id en la url
            val id = call.parameters["id"] ?: return@get call.respondText(
                "id not found",
                status = HttpStatusCode.BadRequest
            )
            //En caso de no encontrarlo responder con los datos y su status
            val user = users.find { it.id == id.toInt() } ?: return@get call.respondText(
                "User with the $id not found",
                status = HttpStatusCode.NotFound)
            //En caso de no encontrar el usuario responder la llamada con un texto y un status
            call.respond(user)
        }
        //Insertar un usuario, objeto en json para parceo
        post {
            //Creamos una variable, hacemos la llamada y le decimos que recibe un objeto de tipo User para posterior hacer el parseo
            val user = call.receive<User>()
            //Agregamos el usuario a la lista
            users.add(user)
            //Respondemos la llamada
            call.respondText("User created successfully", status = HttpStatusCode.Created)
        }
        //Borrar un usuario
        //Se le pasa el id por parametro a la url
        delete("{id?}") {
            //Creamos la llamada, en caso de que sea llamada de mala manera responder dicha llamada
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Id not found",
                status = HttpStatusCode.BadRequest
            )
            //Validar el id
            //Validamos el id o lo convertimos a entero,
            if (users.removeIf { it.id == id.toInt() }) {
                //Respondemos en caso de que se haya borrado exitosamente
                call.respondText("User has been deleted successfully", status = HttpStatusCode.Accepted)
            } else {
                //Respondemos en caso de que no se haya encontrado el usuario
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}