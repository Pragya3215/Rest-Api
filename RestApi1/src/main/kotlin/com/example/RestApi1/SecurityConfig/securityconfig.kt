package com.example.RestApi1.SecurityConfig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("dummy")
            .password("{noop}dummypass")  // {noop} means no password encoder is used (for simplicity)
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Disable CSRF (only if necessary)
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/messages").permitAll() // Allow public access
                    .anyRequest().authenticated() // Other requests require authentication
            }
            .httpBasic {} // Enable Basic Authentication




        return http.build()
    }
}

