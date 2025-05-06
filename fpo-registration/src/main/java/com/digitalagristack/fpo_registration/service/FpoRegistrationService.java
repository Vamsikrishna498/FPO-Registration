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

    // Create
    public FpoRegistration registerFpo(FpoRegistrationRequest req) {
        validateRequest(req);
        FpoRegistration entity = buildEntityFromRequest(req);
        return repository.save(entity);
    }

    // Read
    public FpoRegistration getFpoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("FPO not found with id: " + id));
    }

    // Update
    public void updateFpo(Long id, FpoRegistrationRequest req) {
        validateRequest(req);
        FpoRegistration existing = getFpoById(id);

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

        repository.save(existing);
    }

    // Validation helper
    private void validateRequest(FpoRegistrationRequest req) {
        if (req.getSelectedServices().contains(ServiceType.OTHERS) &&
            (req.getOtherService() == null || req.getOtherService().trim().isEmpty())) {
            throw new IllegalArgumentException("Please specify 'Other Service' if 'OTHERS' is selected.");
        }
    }

    // Mapping helper
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
}
