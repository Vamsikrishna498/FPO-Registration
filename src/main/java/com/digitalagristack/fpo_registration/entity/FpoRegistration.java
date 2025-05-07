package com.digitalagristack.fpo_registration.entity;

import com.digitalagristack.fpo_registration.Enum.ServiceType; 
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FpoRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "FPO Name is required")
    private String fpoName;

    @NotBlank(message = "FPO Registration Number is required")
    private String fpoRegNumber;

    @NotBlank(message = "FPO CEO Name is required")
    private String fpoCeoName;

    @NotBlank(message = "Mobile number is required")
    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 digits")
    private String mobile;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Total number of members is required")
    private Integer totalMembers;

    @NotNull(message = "Number of acres is required")
    private Double noOfAcres;

    // Address Info
    private String hNo;
    private String village;
    private String addressState;
    private String pincode;

    // Services
    @ElementCollection(targetClass = ServiceType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "fpo_selected_services", joinColumns = @JoinColumn(name = "fpo_id"))
    @Column(name = "service")
    @NotEmpty(message = "At least one service must be selected")
    private List<ServiceType> selectedServices;

    private String otherService;
}
