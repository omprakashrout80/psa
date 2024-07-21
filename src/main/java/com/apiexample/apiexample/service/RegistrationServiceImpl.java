package com.apiexample.apiexample.service;

import com.apiexample.apiexample.Exception.ResourceNotFound;
import com.apiexample.apiexample.dtopayload.RegistrationDto;
import com.apiexample.apiexample.entity.Registration;
import com.apiexample.apiexample.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedReg = registrationRepository.save(registration);
        RegistrationDto dto=mapToDto(savedReg);
        return dto;
    }

    @Override
    public void deleteRegistrationById(Long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(Long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration registration=opReg.get();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    @Override
    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
        //List<Registration> registrations = registrationRepository.findAll();
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable =PageRequest.of(pageNo,pageSize, sort);
        Page<Registration> allEmps=registrationRepository.findAll(pageable);
        List<Registration> allReg=allEmps.getContent();
        List<RegistrationDto> dto=allReg.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public RegistrationDto getRegistrationById(long id) {
        Registration registration=registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Registration not found with id :"+id)
        );
        RegistrationDto dto = mapToDto(registration);
        return dto;
    }


    Registration mapToEntity(RegistrationDto dto){
        Registration reg=new Registration();
        reg.setName(dto.getName());
        reg.setEmail(dto.getEmail());
        reg.setMobile(dto.getMobile());
        return reg;

    }

    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto=new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }

}



