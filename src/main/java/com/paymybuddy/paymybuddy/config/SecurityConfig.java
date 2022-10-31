package com.paymybuddy.paymybuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    /**
     *
     * @param http
     * @return
     * @throws Exception
     */

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .antMatchers("/user/transactions/").authenticated()
                .antMatchers("/transactions/{id}").authenticated()
                .antMatchers("/transactions/nouvelleTransaction").authenticated()
                .antMatchers("/utilisateurs/").authenticated()
                .antMatchers("/connexions/{id}").authenticated()
                .antMatchers("/connexions/nouvelleConnexion").authenticated()
                .antMatchers("/login/signing").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl("/login/signing")
                .defaultSuccessUrl("/user/accueil")
                .and()
                .cors().and()
                .csrf().disable().authorizeHttpRequests()
                .and()
                .build();
    }
    @Configuration
    public class WebConfiguration implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedMethods("*");
        }
    }

    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails>userDetailsList =new ArrayList<>();
        userDetailsList.add(User.withUsername("user").password(passwordEncoder().encode("password")).roles("Utilisateur").build());
        return new InMemoryUserDetailsManager(userDetailsList);
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        protected void handle(HttpServletRequest request, HttpServletResponse response,
                              Authentication authentication) throws IOException, ServletException {
        }
    }
        @Bean
        public AuthenticationSuccessHandler appAuthenticationSuccessHandler(){
            return new AppAuthenticationSuccessHandler();
        }

}
