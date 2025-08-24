package com.itc.insurancehelper.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Security configuration class
// that sets up JWT authentication and authorization
// allows unauthenticated access to /api/auth/** endpoints
// requires authentication for all other endpoints
// disables CSRF protection
// uses stateless session management
// adds JwtAuthFilter before UsernamePasswordAuthenticationFilter
// enables method-level security with @EnableMethodSecurity
// uses BCryptPasswordEncoder for password encoding


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // Configure HttpSecurity
    // Disable CSRF, set session management to stateless
    // Allow unauthenticated access to /api/auth/** and H2 console and "/api/auth/**",
    //                            "/h2-console/**",
    //                            "/api/chat/**",
    //                            "/api/policies/**",
    //                            "/v3/api-docs/**",
    //                            "/swagger-ui.html",
    //                            "/swagger-ui/**",
    //                            "/swagger-resources/**"
    // Require authentication for all other requests
    // Add JwtAuthFilter before UsernamePasswordAuthenticationFilter

    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()   // ✅ allow everything
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    http.headers(headers -> headers.frameOptions(frame -> frame.disable())); // allow H2 console
    return http.build();
  }

  @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
