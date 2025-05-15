package com.digitalagristack.fpo_registration;

import com.digitalagristack.fpo_registration.Enum.ServiceType;
import com.digitalagristack.fpo_registration.dto.FpoRegistrationRequest;
import com.digitalagristack.fpo_registration.entity.FpoRegistration;
import com.digitalagristack.fpo_registration.exception.FpoNotFoundException;
import com.digitalagristack.fpo_registration.exception.InvalidFpoDataException;
import com.digitalagristack.fpo_registration.mapper.FpoRegistrationMapper;
import com.digitalagristack.fpo_registration.repository.FpoRegistrationRepository;
import com.digitalagristack.fpo_registration.service.FpoRegistrationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FpoRegistrationServiceTest {

    private FpoRegistrationRepository repository;
    private FpoRegistrationService service;

    @BeforeEach
    public void setup() {
        repository = mock(FpoRegistrationRepository.class);
        service = new FpoRegistrationService(repository);
    }

    @Test
    public void testRegisterFpo_Success() {
        FpoRegistrationRequest req = new FpoRegistrationRequest();
        req.setFpoName("Test FPO");
        req.setSelectedServices(List.of(ServiceType.SOIL_TEST));
        FpoRegistration expected = FpoRegistrationMapper.toEntity(req);

        when(repository.save(any(FpoRegistration.class))).thenReturn(expected);

        FpoRegistration saved = service.registerFpo(req);

        assertEquals("Test FPO", saved.getFpoName());
        verify(repository, times(1)).save(any(FpoRegistration.class));
    }

    @Test
    public void testRegisterFpo_ThrowsException_WhenOtherServiceIsEmpty() {
        FpoRegistrationRequest req = new FpoRegistrationRequest();
        req.setSelectedServices(List.of(ServiceType.OTHERS));
        req.setOtherService("");

        InvalidFpoDataException thrown = assertThrows(InvalidFpoDataException.class, () -> {
            service.registerFpo(req);
        });

        assertEquals("Please specify 'Other Service' if 'OTHERS' is selected.", thrown.getMessage());
    }

    @Test
    public void testGetFpoById_Found() {
        FpoRegistration entity = new FpoRegistration();
        entity.setId(1L);
        entity.setFpoName("Found FPO");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        FpoRegistration result = service.getFpoById(1L);

        assertEquals("Found FPO", result.getFpoName());
    }

    @Test
    public void testGetFpoById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FpoNotFoundException.class, () -> service.getFpoById(1L));
    }
}
