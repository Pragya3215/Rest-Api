package com.example.RestApi1.Service
import  com.example.RestApi1.model.Message
import  com.example.RestApi1.model.MessageDTO
import  com.example.RestApi1.Repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageRepository: MessageRepository) {
    fun createMessage(messageDTO: MessageDTO): Message =
        messageRepository.save(Message(content = messageDTO.content))

    fun getAllMessages(): List<Message> = messageRepository.findAll()

    fun getMessageById(id: String): Message? = messageRepository.findById(id).orElse(null)

    fun deleteMessage(id: String) = messageRepository.deleteById(id)
}