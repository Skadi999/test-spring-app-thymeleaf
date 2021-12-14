package com.skadi.testspringproject

import javax.persistence.*

//todo the age field is int and if we make it blank it will throw an exception, try to fix later.
@Entity
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,

    val name: String = "",

    val lastName: String = "",

    val age: Int = 0,

    val username: String = "",

    val password: String = "",

    val email: String = "",

    val avatarUrl: String = "",

    val description: String = "",

    val role: String = "USER"
)