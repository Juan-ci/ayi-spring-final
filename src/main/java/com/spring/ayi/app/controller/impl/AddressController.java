package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IAddressController;
import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.service.IAddressService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Api(value = "Address Api", tags = "{Address Service}")
@RequestMapping(value = "/address")
public class AddressController implements IAddressController {

    private IAddressService addressService;

    @Override
    public ResponseEntity<?> createAddress(AddressRequest request) {
        AddressResponse addressResponse = addressService.createAddress(request);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }
}
