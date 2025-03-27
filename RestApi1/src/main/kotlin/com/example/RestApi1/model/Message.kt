package com.example.RestApi1.model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "messages")
data class Message(
    @Id val id: String? = null,
    val content: String
)