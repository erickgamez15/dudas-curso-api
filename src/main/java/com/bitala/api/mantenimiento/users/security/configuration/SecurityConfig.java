package com.bitala.api.mantenimiento.users.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bitala.api.mantenimiento.users.security.jwt.CustomerDetailsService;
import com.bitala.api.mantenimiento.users.security.jwt.JwtFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired 
    private CustomerDetailsService customerDetailsService;

    @Autowired 
    private JwtFilter jwtFilter;
     
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        //Se deshabilitia csrf ya que Spring provee su propia seguridad
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authRequest -> authRequest.requestMatchers(
                "/api/usuario/login",
                "/api/usuario/forgot-password", 
                "/api/usuario/signup").permitAll()
                .anyRequest().authenticated()
            ).formLogin(Customizer.withDefaults());
            /*.exceptionHandling(customize -> customize
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN))
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);*/
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        //Se usa BCryptPasswordEncoder como una implementación segura para cifrar las contraseñas. Utiliza el algoritmo de hash bcrypt, que es una de las opciones más seguras y recomendadas para almacenar contraseñas.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
