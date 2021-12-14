package com.skadi.testspringproject

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PersonRepository: CrudRepository<Person, Int> {
    @Modifying
    @Transactional
    @Query("update Person p set p.avatarUrl='" + Constants.DEFAULT_AVATAR + "' where p.id = :id")
    fun setDefaultAvatar(@Param("id") id: Int)

    @Query("select p from Person p where p.username = :username")
    fun getPersonByUsername(@Param("username") username: String): Person
}