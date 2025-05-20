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

    /**
     * Sets up the mocked repository and the service instance before each test case.
     */
    @BeforeEach
    public void setup() {
        repository = mock(FpoRegistrationRepository.class);
        service = new FpoRegistrationService(repository);
    }

    /**
     * Test case: Successful FPO registration with valid data.
     * Verifies that the service saves the FPO and returns the expected values.
     */
    @Test
    public void testRegisterFpo_Success() {
        // Prepare a sample request
        FpoRegistrationRequest req = new FpoRegistrationRequest();
        req.setFpoName("Test FPO");
        req.setSelectedServices(List.of(ServiceType.SOIL_TEST));

        // Expected entity after mapping
        FpoRegistration expected = FpoRegistrationMapper.toEntity(req);

        // Mock repository behavior
        when(repository.save(any(FpoRegistration.class))).thenReturn(expected);

        // Call the service
        FpoRegistration saved = service.registerFpo(req);

        // Assert the FPO name matches
        assertEquals("Test FPO", saved.getFpoName());

        // Verify that save was called once
        verify(repository, times(1)).save(any(FpoRegistration.class));
    }

    /**
     * Test case: Invalid registration request where 'OTHERS' is selected but 'otherService' is empty.
     * Expects InvalidFpoDataException to be thrown with a proper message.
     */
    @Test
    public void testRegisterFpo_ThrowsException_WhenOtherServiceIsEmpty() {
        // Prepare request with 'OTHERS' selected but no 'otherService' provided
        FpoRegistrationRequest req = new FpoRegistrationRequest();
        req.setSelectedServices(List.of(ServiceType.OTHERS));
        req.setOtherService("");

        // Expect the exception and check its message
        InvalidFpoDataException thrown = assertThrows(InvalidFpoDataException.class, () -> {
            service.registerFpo(req);
        });

        assertEquals("Please specify 'Other Service' if 'OTHERS' is selected.", thrown.getMessage());
    }

    /**
     * Test case: Fetching an existing FPO by ID.
     * Verifies that the correct FPO is returned when found.
     */
    @Test
    public void testGetFpoById_Found() {
        // Create mock entity
        FpoRegistration entity = new FpoRegistration();
        entity.setId(1L);
        entity.setFpoName("Found FPO");

        // Mock repository to return the entity
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        // Call the service method
        FpoRegistration result = service.getFpoById(1L);

        // Assert FPO name is as expected
        assertEquals("Found FPO", result.getFpoName());
    }

    /**
     * Test case: Fetching a non-existent FPO by ID.
     * Expects FpoNotFoundException to be thrown when no record is found.
     */
    @Test
    public void testGetFpoById_NotFound() {
        // Mock repository to return empty
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Expect exception when FPO is not found
        assertThrows(FpoNotFoundException.class, () -> service.getFpoById(1L));
    }
}
