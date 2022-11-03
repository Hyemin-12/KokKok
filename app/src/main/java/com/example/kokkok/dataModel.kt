package com.example.kokkok

import java.io.Serializable

data class RegisterModel(
    var id: String,
    var pw: String,
    var name: String
)

data class RegisterResult(
    var message: Boolean
)

data class LoginModel(
    var id: String,
    var pw: String
)

data class LoginResult(
    var UID: Int
)

data class User(
    val UID: Int,
    val id: String,
    val password: String,
    var name: String
): Serializable

data class UserId(
    val id: String
)
