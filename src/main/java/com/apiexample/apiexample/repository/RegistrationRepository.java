package com.apiexample.apiexample.repository;

import com.apiexample.apiexample.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}