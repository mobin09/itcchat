package com.itc.insurancehelper.auth;

import com.itc.insurancehelper.user.UserEntity;
import com.itc.insurancehelper.user.UserRepository;
import com.itc.insurancehelper.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


// AuthController class to handle user registration and login
// It has two endpoints: /register and /login
// The /register endpoint accepts a RegisterRequest body
// and creates a new user using the UserService
// The /login endpoint accepts a LoginRequest body
// and authenticates the user using the UserRepository and PasswordEncoder
// If authentication is successful, it generates a JWT token using the JwtService
// and returns it in a TokenResponse
// The controller uses @RestController and @RequestMapping annotations
// to define the base path for the endpoints

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
    userService.register(req.getUsername(), req.getEmail(), req.getPassword());
    return ResponseEntity.status(HttpStatus.CREATED).body(java.util.Map.of("message", "User registered successfully"));
  }

  // generate the testing controller
  @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World!");
    }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest req) {
    var user = userRepository.findByUsername(req.getUsername())
        .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    if (!encoder.matches(req.getPassword(), user.getPasswordHash())) throw new IllegalArgumentException("Invalid credentials");
    String token = jwtService.generateToken(req.getUsername());
    return ResponseEntity.ok(new TokenResponse(token));
  }
}
