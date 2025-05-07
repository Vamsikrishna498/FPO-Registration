package com.digitalagristack.fpo_registration.Controller;

import com.digitalagristack.fpo_registration.dto.FpoRegistrationRequest;
import com.digitalagristack.fpo_registration.entity.FpoRegistration;
import com.digitalagristack.fpo_registration.service.FpoRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fpo")
public class FpoRegistrationController {

    private final FpoRegistrationService service;

    public FpoRegistrationController(FpoRegistrationService service) {
        this.service = service;
    }

    // POST: Create new registration
    @PostMapping("/register")
    public ResponseEntity<String> registerFpo(@Valid @RequestBody FpoRegistrationRequest request) {
        service.registerFpo(request);
        return ResponseEntity.ok("Thank you for registering! Our team will contact you shortly.");
    }

    // GET: Fetch FPO by ID
    @GetMapping("/{id}")
    public ResponseEntity<FpoRegistration> getFpoById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFpoById(id));
    }

    // PUT: Update existing FPO registration
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFpo(@PathVariable Long id, @Valid @RequestBody FpoRegistrationRequest request) {
        service.updateFpo(id, request);
        return ResponseEntity.ok("FPO registration updated successfully.");
    }
}

