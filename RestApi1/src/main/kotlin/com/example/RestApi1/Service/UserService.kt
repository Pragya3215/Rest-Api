package com.example.RestApi1.Service
import  com.example.RestApi1.model.User
import  com.example.RestApi1.model.UserDTO
import  com.example.RestApi1.Repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    fun registerUser(userDTO: UserDTO): User {
        val encodedPassword = passwordEncoder.encode(userDTO.password)
        val user = User(username = userDTO.username, password = encodedPassword)

        println("Saving new user: ${user.username}")  // Debug log
        return userRepository.save(user)



    }


    fun validateUser(userDTO: UserDTO): Boolean {
        val user = userRepository.findByUsername(userDTO.username) ?: return false
        return passwordEncoder.matches(userDTO.password, user.password)
    }
}