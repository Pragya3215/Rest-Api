package com.example.RestApi1.model
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "users")
@TypeAlias("user")
data class User(
    @Id val id: String? = null,
    val username: String,
    val password: String
)
