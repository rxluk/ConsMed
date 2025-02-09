package com.app.consMed.Config;

import com.app.consMed.Security.SecurityFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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
@SecurityScheme(
        name = "Bearer Auth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    private static final String[] PERMIT_ALL = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/h2-console/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PERMIT_ALL).permitAll()
                        // Endpoints de autenticação
                        .requestMatchers(HttpMethod.GET, "/auth/role").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/user/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/auth/admin/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/auth/recepcionista/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/auth/medico/register").hasRole("ADMIN")

                        // Endpoints de consulta com role RECEPCIONISTA
                        .requestMatchers(HttpMethod.GET, "/consulta/get/{id}").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.GET, "/consulta/get/all").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.POST, "/consulta/create").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.PUT, "/consulta/update/{id}").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.DELETE, "/consulta/delete/{id}").hasRole("RECEPCIONISTA")

                        // Endpoints de administrador
                        .requestMatchers(HttpMethod.GET, "/admin/get/{cpf}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin/get/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/admin/delete/{cpf}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/admin/update/{cpf}").hasRole("ADMIN")

                        // Endpoints de recepcionista
                        .requestMatchers(HttpMethod.GET, "/recepcionista/get/{cpf}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/recepcionista/get/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/recepcionista/delete/{cpf}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/recepcionista/update/{cpf}").hasRole("ADMIN")

                        // Endpoints de usuário
                        .requestMatchers(HttpMethod.GET, "/user/get/{login}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/get/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/delete/{login}").hasRole("ADMIN")

                        // Endpoints de médico
                        .requestMatchers(HttpMethod.GET, "/medico/get/{cpf}").hasRole("ADMIN, RECEPCIONISTA")
                        .requestMatchers(HttpMethod.GET, "/medico/get/{crm}").hasRole("ADMIN, RECEPCIONISTA")
                        .requestMatchers(HttpMethod.GET, "/medico/get/all").hasRole("ADMIN, RECEPCIONISTA")
                        .requestMatchers(HttpMethod.GET, "/medico/get/{especiadade}").hasRole("ADMIN, RECEPCIONISTA")
                        .requestMatchers(HttpMethod.DELETE, "/medico/delete/{cpf}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/medico/update/{cpf}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/put/{login}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/get/all-with-password").hasRole("ADMIN")

                        // Endpoints de disponibilidade (DisponibilidadeController)
                        .requestMatchers(HttpMethod.GET, "/disponibilidades").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.GET, "/disponibilidades/{id}").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.GET, "/disponibilidades/medico/{medicoId}").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.POST, "/disponibilidades").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.PUT, "/disponibilidades/{id}").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.DELETE, "/disponibilidades/{id}").hasRole("RECEPCIONISTA")

                        // Endpoints de paciente (PacienteController) - com base no /paciente
                        .requestMatchers(HttpMethod.GET, "/paciente/get/{cpf}").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.GET, "/paciente/get/all").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.POST, "/paciente/create").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.PUT, "/paciente/update/{cpf}").hasRole("RECEPCIONISTA")
                        .requestMatchers(HttpMethod.DELETE, "/paciente/delete/{cpf}").hasRole("RECEPCIONISTA")

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