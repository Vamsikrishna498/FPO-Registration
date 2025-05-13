package com.digitalagristack.fpo_registration.service;

import com.digitalagristack.fpo_registration.Enum.ServiceType;
import com.digitalagristack.fpo_registration.dto.FpoRegistrationRequest;
import com.digitalagristack.fpo_registration.entity.FpoRegistration;
import com.digitalagristack.fpo_registration.exception.FpoNotFoundException;
import com.digitalagristack.fpo_registration.exception.InvalidFpoDataException;
import com.digitalagristack.fpo_registration.mapper.FpoRegistrationMapper;
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
        FpoRegistration entity = FpoRegistrationMapper.toEntity(req);
        return repository.save(entity);
    }

    // Read
    public FpoRegistration getFpoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FpoNotFoundException("FPO not found with id: " + id));
    }

    // Update
    public void updateFpo(Long id, FpoRegistrationRequest req) {
        validateRequest(req);
        FpoRegistration existing = getFpoById(id);
        FpoRegistrationMapper.updateEntity(existing, req);
        repository.save(existing);
    }

    // Validation helper
    private void validateRequest(FpoRegistrationRequest req) {
        if (req.getSelectedServices().contains(ServiceType.OTHERS) &&
            (req.getOtherService() == null || req.getOtherService().trim().isEmpty())) {
            throw new InvalidFpoDataException("Please specify 'Other Service' if 'OTHERS' is selected.");
        }
    }
}
