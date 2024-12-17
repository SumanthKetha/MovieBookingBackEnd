package com.movie.ticketbooking.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Enable CORS globally
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (optional, for stateless APIs like JWT)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOriginPattern("*");  // Allow frontend domain
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Allowed HTTP methods
                    config.setAllowedHeaders(Arrays.asList("*"));  // Allow all headers
                    config.setAllowCredentials(true);  // Allow credentials (cookies, etc.)
                    return config;
                }))

                // Allow all requests without authentication (use this carefully for production)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/**").permitAll()  // Allow all requests (no authentication required)
                        .requestMatchers("OPTIONS", "/**").permitAll()  // Explicitly allow OPTIONS pre-flight requests
                        .anyRequest().authenticated()  // Authenticate other requests (if needed)
                );

        return http.build();
    }
}
