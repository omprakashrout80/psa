package com.apiexample.apiexample.controller;

import com.apiexample.apiexample.dtopayload.RegistrationDto;
import com.apiexample.apiexample.entity.Registration;
import com.apiexample.apiexample.service.RegistrationService;
import com.apiexample.apiexample.service.RegistrationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    //http://localhost://api/v1/registration

    @PostMapping
    public ResponseEntity<RegistrationDto> getRegistration(@RequestBody RegistrationDto registrationDto) {
        RegistrationDto savedUser = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    //http://localhost:8080/api/v1/registration?id=
    @DeleteMapping
    public ResponseEntity<String> deleteRegistrationById(@RequestParam Long id){
        registrationService.deleteRegistrationById(id);
        return new ResponseEntity<>("Record Deleted",HttpStatus.OK);

    }


    //http://localhost:8080/api/v1/registration?id=
    @PutMapping
    public ResponseEntity<RegistrationDto> updateRegistration(
            @RequestParam Long id,@RequestBody RegistrationDto registrationDto){
        RegistrationDto dto=registrationService.updateRegistration(id,registrationDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/registration?pageNo=0&pageSize=3&sortBy=id&SortDir=acs
    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(
            @RequestParam(name ="pageNo" ,defaultValue = "0",required = false) int pageNo,
            @RequestParam(name ="pageSize" ,defaultValue = "3",required = false) int pageSize,
            @RequestParam(name ="sortBy" ,defaultValue = "name",required = false) String sortBy,
            @RequestParam(name ="sortDir" ,defaultValue = "name",required = false) String sortDir
    ){
        List<RegistrationDto> dto=registrationService.getAllRegistrations(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost://api/v1/registration/byId?id=1

    @GetMapping("/byId")
    public ResponseEntity<RegistrationDto> getRegById(@RequestParam long id){
        RegistrationDto dto=registrationService.getRegistrationById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}



