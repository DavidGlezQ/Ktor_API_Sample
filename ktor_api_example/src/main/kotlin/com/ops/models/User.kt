package com.ops.models

import kotlinx.serialization.Serializable

/* DataClass ejemplo para un usuario, se agrega la anotacion de @Serializable para poder serializar el obejro a json */
@Serializable
data class User(val id: Int, val name: String, val age: Int, val email: String)
