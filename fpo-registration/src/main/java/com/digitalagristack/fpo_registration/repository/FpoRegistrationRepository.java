package com.digitalagristack.fpo_registration.repository;

import com.digitalagristack.fpo_registration.entity.FpoRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FpoRegistrationRepository extends JpaRepository<FpoRegistration, Long> {
}
