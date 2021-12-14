package com.skadi.testspringproject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class PersonDetailsService: UserDetailsService {
    @Autowired
    private lateinit var personRepository: PersonRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) throw UsernameNotFoundException("Username is null!")

        val user = personRepository.getPersonByUsername(username)
        return PersonDetails(user)
    }
}