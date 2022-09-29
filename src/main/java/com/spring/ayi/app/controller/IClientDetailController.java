package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.entity.ClientDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IClientDetailController {

    @PostMapping(path = "/create-client-detail",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a client detail created",
            httpMethod = "POST",
            response = ClientDetailResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about client",
                    response = ClientDetailResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<?> createClientDetail(
            @ApiParam(value = "data of client detail", required = true)
            @RequestBody ClientDetailRequest request
    );
}
