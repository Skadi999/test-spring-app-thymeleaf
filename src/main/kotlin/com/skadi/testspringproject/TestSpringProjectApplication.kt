package com.skadi.testspringproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = PersonRepository)
class TestSpringProjectApplication

fun main(args: Array<String>) {
	runApplication<TestSpringProjectApplication>(*args)
	println("*** App Started ***")
}
