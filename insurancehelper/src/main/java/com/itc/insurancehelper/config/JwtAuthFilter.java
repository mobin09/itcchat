package com.itc.insurancehelper.config;

import com.itc.insurancehelper.auth.JwtService;
import com.itc.insurancehelper.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

// JWT authentication filter
// that extracts JWT from Authorization header
// validates the token and sets the authentication in the security context
// if the token is valid and corresponds to a user in the database
// ignores any exceptions during token processing



@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwtService; private final UserRepository userRepository;
  @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, jakarta.servlet.ServletException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      try {
        String username = jwtService.extractUsername(token);
        var user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
          var auth = new UsernamePasswordAuthenticationToken(username, null,
              user.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet()));
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      } catch (Exception ignored) { }
    }
    chain.doFilter(request, response);
  }
}
