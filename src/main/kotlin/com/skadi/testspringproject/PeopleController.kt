package com.skadi.testspringproject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/people")
class PeopleController {
    //todo read more about annotations in Spring and Spring Boot
    //todo chat, authentication (Spring Security)
    //todo in the far future: testing, also read more docs

    @Autowired
    val personService = PersonService()

    @GetMapping
    fun showPeople(model: Model): String {
        model["people"] = personService.getAllPeople()
        return "people/all"
    }

    @GetMapping("/{id}")
    fun showPerson(@PathVariable("id") id: Int, model: Model): String {
        model["person"] = personService.getPersonById(id) ?: return "redirect:/people"
        return "people/person"
    }

    @GetMapping("/new")
    fun newPerson(model: Model): String {
        model["person"] = Person()
        return "people/new"
    }
    //Note: Changed Person to CreatePerson
    @PostMapping("")
    fun postPerson(@ModelAttribute("person") @Valid person: CreatePerson, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "people/new"
        personService.addPerson(person)
        if (person.avatarUrl.isBlank()) {
            personService.setDefaultAvatar(person)
        }
        return "redirect:/people/"
    }

    @GetMapping("/{id}/edit")
    fun edit(model: Model, @PathVariable("id") id: Int): String {
        model["person"] = personService.getPersonById(id) ?: return "redirect:/people"
        return "people/edit"
    }

    //todo!!!
    @PatchMapping("/{id}")
    fun updatePerson(
        @PathVariable("id") id: Int,
        @ModelAttribute("person") @Valid person: CreatePerson,
        bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) return "people/edit"
        personService.updatePerson(person, id)
        return "redirect:/people/${id}"
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable("id") id: Int): String {
        personService.deletePerson(id)
        return "redirect:/people"
    }
}