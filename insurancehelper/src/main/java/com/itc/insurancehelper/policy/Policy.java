package com.itc.insurancehelper.policy;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;


// Policy entity with id, policyNumber, type, premium, startDate, endDate and status
// policyNumber is unique and not null
// type is not null
// premium is not null and positive
// startDate and endDate are not null
// status is not null
// use Lombok to generate getters, setters, constructors and builder
// use JPA annotations to map to database table "policies"


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(unique = true) private String policyNumber;
    @NotBlank private String type;
    @NotNull @Positive private BigDecimal premium;
    @NotNull private LocalDate startDate;
    @NotNull private LocalDate endDate;
    @NotBlank private String status;
}
