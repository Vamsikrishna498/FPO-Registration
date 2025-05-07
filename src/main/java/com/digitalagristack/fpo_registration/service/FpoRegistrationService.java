package com.digitalagristack.fpo_registration.service;

import com.digitalagristack.fpo_registration.dto.FpoRegistrationRequest;
import com.digitalagristack.fpo_registration.entity.FpoRegistration;
import com.digitalagristack.fpo_registration.Enum.ServiceType;
import com.digitalagristack.fpo_registration.repository.FpoRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class FpoRegistrationService {

    private final FpoRegistrationRepository repository;

    public FpoRegistrationService(FpoRegistrationRepository repository) {
        this.repository = repository;
    }

    // Register new FPO
    public FpoRegistration registerFpo(FpoRegistrationRequest req) {
        validateRequest(req);
        FpoRegistration entity = buildEntityFromRequest(req);
        return repository.save(entity);
    }

    // Get FPO by ID
    public FpoRegistration getFpoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("FPO not found with ID: " + id));
    }

    // Update FPO
    public FpoRegistration updateFpo(Long id, FpoRegistrationRequest req) {
        validateRequest(req);
        FpoRegistration existing = getFpoById(id);
        updateEntity(existing, req);
        return repository.save(existing);
    }

    // Validate the request
    private void validateRequest(FpoRegistrationRequest req) {
        if (req.getSelectedServices().contains(ServiceType.OTHERS) &&
            (req.getOtherService() == null || req.getOtherService().trim().isEmpty())) {
            throw new IllegalArgumentException("Please specify 'Other Service' if 'OTHERS' is selected.");
        }
    }

    // Build entity for registration
    private FpoRegistration buildEntityFromRequest(FpoRegistrationRequest req) {
        return FpoRegistration.builder()
                .country(req.getCountry())
                .state(req.getState())
                .district(req.getDistrict())
                .fpoName(req.getFpoName())
                .fpoRegNumber(req.getFpoRegNumber())
                .fpoCeoName(req.getFpoCeoName())
                .mobile(req.getMobile())
                .email(req.getEmail())
                .totalMembers(req.getTotalMembers())
                .noOfAcres(req.getNoOfAcres())
                .hNo(req.getHNo())
                .village(req.getVillage())
                .addressState(req.getAddressState())
                .pincode(req.getPincode())
                .selectedServices(req.getSelectedServices())
                .otherService(req.getOtherService())
                .build();
    }

    // Update existing entity fields
    private void updateEntity(FpoRegistration existing, FpoRegistrationRequest req) {
        existing.setCountry(req.getCountry());
        existing.setState(req.getState());
        existing.setDistrict(req.getDistrict());
        existing.setFpoName(req.getFpoName());
        existing.setFpoRegNumber(req.getFpoRegNumber());
        existing.setFpoCeoName(req.getFpoCeoName());
        existing.setMobile(req.getMobile());
        existing.setEmail(req.getEmail());
        existing.setTotalMembers(req.getTotalMembers());
        existing.setNoOfAcres(req.getNoOfAcres());
        existing.setHNo(req.getHNo());
        existing.setVillage(req.getVillage());
        existing.setAddressState(req.getAddressState());
        existing.setPincode(req.getPincode());
        existing.setSelectedServices(req.getSelectedServices());
        existing.setOtherService(req.getOtherService());
    }
}
