package com.example.RestApi1.Controller
import  com.example.RestApi1.model.Message
import  com.example.RestApi1.model.MessageDTO
import  com.example.RestApi1.Service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessageController (private val messageService: MessageService) {
    @PostMapping
    fun createMessage(@RequestBody messageDTO: MessageDTO): ResponseEntity<Message> =
        ResponseEntity.ok(messageService.createMessage(messageDTO))

     //Endpoint for retrieving all messages.

    @GetMapping
    fun getAllMessages(): ResponseEntity<List<Message>> = ResponseEntity.ok(messageService.getAllMessages())


      //Endpoint for retrieving a specific message by ID.

    @GetMapping("/{id}")
    fun getMessageById(@PathVariable id: String): ResponseEntity<Message> =
        messageService.getMessageById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()


      //Endpoint for deleting a message by ID.

    @DeleteMapping("/{id}")
    fun deleteMessage(@PathVariable id: String): ResponseEntity<String> {
        messageService.deleteMessage(id)
        return ResponseEntity.ok("Message deleted successfully")
    }
}