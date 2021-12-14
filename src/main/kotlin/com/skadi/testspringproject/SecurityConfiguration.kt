package com.skadi.testspringproject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        if (auth == null) throw NullPointerException("Authentication Manager is null")
        auth.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/people/new").hasRole("ADMIN")
            .antMatchers("/people").hasAnyRole("USER", "ADMIN")
            .antMatchers("/").permitAll()
            .and().formLogin()
            .and()
            .logout()
            .permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .permitAll()

    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        return PersonDetailsService()
//        val userAdmin = User.withDefaultPasswordEncoder()
//            .username("skadi101")
//            .password("p27691ti")
//            .roles("ADMIN")
//            .build()
//
//        val user = User.withDefaultPasswordEncoder()
//            .username("skadi102")
//            .password("p27691ti")
//            .roles("USER")
//            .build()
//        return InMemoryUserDetailsManager(userAdmin, user)
    }
}