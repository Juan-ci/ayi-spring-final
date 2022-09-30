package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IInvoiceController {

    @PostMapping(value = "/create-invoice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a invoice created",
            httpMethod = "POST",
            response = InvoiceResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about invoice",
                    response = InvoiceResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<?> createInvoice(
            @ApiParam(value = "data of invoice", required = true)
            @RequestBody InvoiceRequest request
    );
}
