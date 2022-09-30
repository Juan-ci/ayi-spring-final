package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IInvoiceController;
import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import com.spring.ayi.app.service.IInvoiceService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(value = "Invoice Api", tags = "{Invoice Service}")
@RequestMapping(path = "/invoice")
public class InvoiceControllerImpl implements IInvoiceController {

    private IInvoiceService invoiceService;

    @Override
    public ResponseEntity<?> createInvoice(InvoiceRequest request) {
        InvoiceResponse response = invoiceService.createInvoice(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
