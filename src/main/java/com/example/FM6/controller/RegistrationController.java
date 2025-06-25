package com.example.FM6.controller;

import com.example.FM6.dto.RegistrationRequest;
import com.example.FM6.entity.Adherent;
import com.example.FM6.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public Adherent registerFamily(@RequestBody RegistrationRequest request) {
        return registrationService.registerFamily(request);
    }
}
