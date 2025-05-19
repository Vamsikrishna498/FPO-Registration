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
    @Size(max = 50, message = "Country must not exceed 50 characters")
    private String country;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State must not exceed 50 characters")
    private String state;

    @NotBlank(message = "District is required")
    @Size(max = 50, message = "District must not exceed 50 characters")
    private String district;

    @NotBlank(message = "FPO Name is required")
    @Size(max = 100, message = "FPO Name must not exceed 100 characters")
    private String fpoName;

    @NotBlank(message = "FPO Registration Number is required")
    @Size(max = 50, message = "FPO Registration Number must not exceed 50 characters")
    private String fpoRegNumber;

    @NotBlank(message = "FPO CEO Name is required")
    @Size(max = 100, message = "FPO CEO Name must not exceed 100 characters")
    private String fpoCeoName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number must be between 10 and 15 digits")
    private String mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Total number of members is required")
    @Min(value = 1, message = "There must be at least 1 member")
    private Integer totalMembers;

    @NotNull(message = "Number of acres is required")
    @DecimalMin(value = "0.1", inclusive = true, message = "Acreage must be at least 0.1")
    private Double noOfAcres;

    // Address Info
    @NotBlank(message = "House number is required")
    @Size(max = 50, message = "House number must not exceed 50 characters")
    private String hNo;

    @NotBlank(message = "Village is required")
    @Size(max = 50, message = "Village name must not exceed 50 characters")
    private String village;

    @NotBlank(message = "Address state is required")
    @Size(max = 50, message = "Address state must not exceed 50 characters")
    private String addressState;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be exactly 6 digits")
    private String pincode;

    // Services
    @ElementCollection(targetClass = ServiceType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "fpo_selected_services", joinColumns = @JoinColumn(name = "fpo_id"))
    @Column(name = "service")
    @NotEmpty(message = "At least one service must be selected")
    private List<ServiceType> selectedServices;

    @Size(max = 200, message = "Other service description must not exceed 200 characters")
    private String otherService;
}
