package com.example.RestApi1.Controller
import  com.example.RestApi1.model.UserDTO
import  com.example.RestApi1.Service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestBody

@CrossOrigin(origins = ["*"]) // Allow all origins (only for testing)
@RestController
@RequestMapping("/auth")
class UserController (private val userService: UserService)  {
    @PostMapping("/register")
    fun register(@RequestBody userDTO: UserDTO): ResponseEntity<String> {
        println("🚀 Received registration request for: ${userDTO.username}")  // Debug log*/
        userService.registerUser(userDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully")
    }


     //Endpoint for user login.

    @PostMapping("/login")
    fun login(@RequestBody userDTO: UserDTO): ResponseEntity<String> {
        return if (userService.validateUser(userDTO)) {
            ResponseEntity.ok("Login successful")
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials")
        }
    }
}

