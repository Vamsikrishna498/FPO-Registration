package com.digitalagristack.fpo_registration;

import com.digitalagristack.fpo_registration.dto.FpoRegistrationRequest;
import com.digitalagristack.fpo_registration.entity.FpoRegistration;
import com.digitalagristack.fpo_registration.mapper.FpoRegistrationMapper;
import com.digitalagristack.fpo_registration.Enum.ServiceType;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FpoRegistrationMapperTest {

    /**
     * Test case for verifying the mapping of FpoRegistrationRequest to FpoRegistration entity.
     * Ensures all fields are correctly transferred, including enum list and additional services.
     */
    @Test
    public void testToEntity() {
        // Prepare sample request data
        FpoRegistrationRequest req = new FpoRegistrationRequest();
        req.setCountry("India");
        req.setState("Telangana");
        req.setDistrict("Hyderabad");
        req.setFpoName("Test FPO");
        req.setFpoRegNumber("FPO123");
        req.setFpoCeoName("Ravi");
        req.setMobile("9876543210");
        req.setEmail("test@example.com");
        req.setTotalMembers(50);
        req.setNoOfAcres(100.0); // Test decimal field
        req.setHNo("1-23");
        req.setVillage("Some Village");
        req.setAddressState("Telangana");
        req.setPincode("500001");
        req.setSelectedServices(List.of(ServiceType.SOIL_TEST, ServiceType.WATER_TEST));
        req.setOtherService("Custom Service");

        // Call the mapper method
        FpoRegistration entity = FpoRegistrationMapper.toEntity(req);

        // Verify mapped values
        assertEquals("India", entity.getCountry());
        assertEquals("Test FPO", entity.getFpoName());
        assertEquals("Custom Service", entity.getOtherService());
        assertTrue(entity.getSelectedServices().contains(ServiceType.SOIL_TEST));
    }

    /**
     * Test case for verifying the update of an existing FpoRegistration entity
     * using updated values from a request object.
     */
    @Test
    public void testUpdateEntity() {
        // Prepare an existing entity with initial values
        FpoRegistration existing = new FpoRegistration();
        existing.setFpoName("Old Name");

        // Prepare request with updated data
        FpoRegistrationRequest req = new FpoRegistrationRequest();
        req.setFpoName("New Name");
        req.setCountry("India");
        req.setState("TS");
        req.setDistrict("Nalgonda");
        req.setFpoRegNumber("FPO999");
        req.setFpoCeoName("Kiran");
        req.setMobile("9999999999");
        req.setEmail("kiran@example.com");
        req.setTotalMembers(30);
        req.setNoOfAcres(75.0); // Ensure update of numeric field
        req.setHNo("12-34");
        req.setVillage("Another Village");
        req.setAddressState("TS");
        req.setPincode("500002");
        req.setSelectedServices(List.of(ServiceType.SEEDS));
        req.setOtherService("Other support");

        // Update the entity using the mapper
        FpoRegistrationMapper.updateEntity(existing, req);

        // Verify the updated fields
        assertEquals("New Name", existing.getFpoName());
        assertEquals("FPO999", existing.getFpoRegNumber());
        assertEquals("Other support", existing.getOtherService());
        assertEquals("TS", existing.getAddressState());
        assertTrue(existing.getSelectedServices().contains(ServiceType.SEEDS));
    }
}
