package com.agrocheck.core.rest_modules.fields.controller;

import com.agrocheck.core.utils.api.dto.SimpleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FieldController {


    @GetMapping("/fields")
    private ResponseEntity<?> getFields(){
        return new ResponseEntity<>("Fields received", HttpStatus.OK);
    }
}
