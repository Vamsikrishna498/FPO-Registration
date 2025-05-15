package com.digitalagristack.fpo_registration.mapper;

import com.digitalagristack.fpo_registration.dto.FpoRegistrationRequest;
import com.digitalagristack.fpo_registration.entity.FpoRegistration;

public class FpoRegistrationMapper {

    // Map request to new entity
    public static FpoRegistration toEntity(FpoRegistrationRequest req) {
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

    // Update existing entity with request data
    public static void updateEntity(FpoRegistration entity, FpoRegistrationRequest req) {
        entity.setCountry(req.getCountry());
        entity.setState(req.getState());
        entity.setDistrict(req.getDistrict());
        entity.setFpoName(req.getFpoName());
        entity.setFpoRegNumber(req.getFpoRegNumber());
        entity.setFpoCeoName(req.getFpoCeoName());
        entity.setMobile(req.getMobile());
        entity.setEmail(req.getEmail());
        entity.setTotalMembers(req.getTotalMembers());
        entity.setNoOfAcres(req.getNoOfAcres());
        entity.setHNo(req.getHNo());
        entity.setVillage(req.getVillage());
        entity.setAddressState(req.getAddressState());
        entity.setPincode(req.getPincode());
        entity.setSelectedServices(req.getSelectedServices());
        entity.setOtherService(req.getOtherService());
    }
}
