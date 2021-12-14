package com.skadi.testspringproject

import org.hibernate.validator.constraints.URL
import javax.validation.constraints.*

/**
 * Used for POST and PATCH requests, this class is validated by the Hibernate validator.
 * Instances of this class are not stored in DB, rather they are converted to an instance of the Person class,
 * which is the one that WILL be stored in DB.
 * It was necessary to create this class due to conflicts of validation & password encoding.
 */
class CreatePerson(
    val id: Int = 0,

    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(min = 2, max = 32, message = "Name must be at least 2 and at most 32 characters long")
    val name: String = "",

    @field:NotBlank(message = "Surname cannot be blank")
    @field:Size(min = 2, max = 32, message = "Surname must be at least 2 and at most 32 characters long")
    val lastName: String = "",

    @field:NotNull(message = "Age cannot be blank")
    @field:Min(0, message = "Age may not be less than 0")
    @field:Max(130, message = "Age may not be over 130")
    val age: Int = 0,

    @field:NotBlank(message = "Username cannot be blank")
    @field:Size(min = 4, max = 32, message = "Username must be at least 4 and at most 32 characters long")
    val username: String = "",

    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(min = 8, max = 32, message = "Password must be at least 8 and at most 32 characters long")
    val password: String = "",

    @field:NotBlank(message = "Password cannot be blank")
    @field:Email(message = "Email must be valid")
    val email: String = "",

    @field:URL(message = "Invalid URL format")
    val avatarUrl: String = "",

    @field:NotBlank(message = "Description may not be blank")
    @field:Size(min = 12, max = 512, message = "Description must be at least 12 and at most 512 characters long")
    val description: String = "",

    val role: String = "USER"
)