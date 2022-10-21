package com.paymybuddy.paymybuddy.config;

import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;

@CrossOrigin
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    /**
     *
     * @param http
     * @return
     * @throws Exception
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                    .antMatchers("/user/transactions/").authenticated()
                    .antMatchers("/transactions/{id}").authenticated()
                    .antMatchers("/transactions/nouvelleTransaction").authenticated()
                    .antMatchers("/utilisateurs/").authenticated()
                    .antMatchers("/connexions/{id}").authenticated()
                    .antMatchers("/connexions/nouvelleConnexion").authenticated()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .failureUrl("/login")
                    .defaultSuccessUrl("/user/accueil")
                    .and()
                .cors()
                    .and()
                .csrf().disable().authorizeHttpRequests()
                    .and()
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
                new User("user", "{noop}password", Collections.emptyList())
        );
    }

}
