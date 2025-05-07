package com.digitalagristack.fpo_registration.dto;

import com.digitalagristack.fpo_registration.Enum.ServiceType; // ✅ Import added
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class FpoRegistrationRequest {

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
    @NotEmpty(message = "At least one service must be selected")
    private List<ServiceType> selectedServices;

    private String otherService;
}
