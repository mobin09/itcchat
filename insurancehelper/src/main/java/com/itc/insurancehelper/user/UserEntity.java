package com.itc.insurancehelper.user;


import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.Set;
// User entity with id, username, email, passwordHash and roles
// roles is a set of Role enum
// username and email are unique and not null
// passwordHash is not null
// use Lombok to generate getters, setters, constructors and builder
// use JPA annotations to map to database table "users"


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String passwordHash;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}