package com.apiexample.apiexample.service;

import com.apiexample.apiexample.dtopayload.RegistrationDto;

import java.util.List;

public interface RegistrationService {

    public RegistrationDto createRegistration(RegistrationDto registrationDto);

    void deleteRegistrationById(Long id);


    public RegistrationDto updateRegistration(Long id, RegistrationDto registrationDto);

    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir);

    RegistrationDto getRegistrationById(long id);
}
