package com.skadi.testspringproject

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@Controller
class BasicController() {

    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = "Index Page"
        return "index"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

}