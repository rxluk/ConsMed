package com.app.consMed.Config;

import com.app.consMed.Security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints de autenticação
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/user/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/admin/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/recepcionista/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/medico/register").permitAll()

                        // Endpoints de administrador
                        .requestMatchers(HttpMethod.GET, "/admin/get/{cpf}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/get/all").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/admin/delete/{cpf}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/admin/update/{cpf}").permitAll()

                        // Endpoints de recepcionista
                        .requestMatchers(HttpMethod.GET, "/recepcionista/get/{cpf}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/recepcionista/get/all").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/recepcionista/delete/{cpf}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/recepcionista/update/{cpf}").permitAll()

                        // Endpoints de usuário
                        .requestMatchers(HttpMethod.GET, "/user/get/{login}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/get/all").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/delete/{login}").permitAll()

                        // Endpoints de médico
                        .requestMatchers(HttpMethod.GET, "/medico/get/{cpf}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/medico/get/{crm}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/medico/get/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/medico/get/{especiadade}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/medico/delete/{cpf}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/medico/update/{cpf}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/put/{login}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/get/all-with-password").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
