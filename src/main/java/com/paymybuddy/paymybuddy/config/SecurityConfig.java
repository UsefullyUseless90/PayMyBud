package com.paymybuddy.paymybuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * @param http
     * @return
     * @throws Exception
     */

    @Bean
    @CrossOrigin(origins = "*", maxAge = 3600)
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/login/signin").permitAll()
                .antMatchers("user/transactions/").authenticated()
                .antMatchers("user/transactions/{id}").authenticated()
                .antMatchers("user/transactions/nouvelleTransaction").authenticated()
                .antMatchers("/utilisateurs/").authenticated()
                .antMatchers("/utilisateur/{id}").permitAll()
                .antMatchers("user/connexions/{id}").authenticated()
                .antMatchers("user/connexions/nouvelleConnexion").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .build();

    }
    /**
     *
     */

    @Configuration
    public class WebConfiguration implements WebMvcConfigurer {

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(List.of("*"));
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return new CustomUserDetailsService();
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
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
            protected void handle(HttpServletRequest request, HttpServletResponse response,
                                  Authentication authentication) throws IOException, ServletException {
            }
        }

        @Bean
        public AuthenticationSuccessHandler appAuthenticationSuccessHandler() {
            return new AppAuthenticationSuccessHandler();
        }


    }
}

