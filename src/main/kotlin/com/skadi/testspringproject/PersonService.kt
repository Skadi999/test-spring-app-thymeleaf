package com.skadi.testspringproject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service
class PersonService {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun addPerson(person: CreatePerson) {
        val newPerson = Person(
            person.id, person.name, person.lastName,
            person.age, person.username, passwordEncoder.encode(person.password),
            person.email, person.avatarUrl, person.description, person.role
        )

        personRepository.save(newPerson)
    }

    fun updatePerson(createPerson: CreatePerson, id: Int) {
        val person = Person(
            createPerson.id, createPerson.name, createPerson.lastName,
            createPerson.age, createPerson.username, passwordEncoder.encode(createPerson.password),
            createPerson.email, createPerson.avatarUrl, createPerson.description, createPerson.role
        )
        personRepository.save(person)
    }

    fun deletePerson(id: Int) {
        personRepository.deleteById(id)
    }

    fun getPersonById(id: Int): Person? {
        return personRepository.findByIdOrNull(id)
    }

    fun getAllPeople(): List<Person> {
        return personRepository.findAll().toList()
    }

    fun setDefaultAvatar(createPerson: CreatePerson) {
        val person = personRepository.findByIdOrNull(createPerson.id)
        if (person != null) personRepository.setDefaultAvatar(createPerson.id)
    }
}