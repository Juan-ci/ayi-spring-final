package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.marker.MarkerRequest;
import com.spring.ayi.app.dto.response.marker.MarkerResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.service.IMarkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@Api(value = "Marker Api", tags = "{Marker Service}")
@RequestMapping(value = "/marker")
public class MarkerController {

    private IMarkerService markerService;

    @PostMapping(path = "/createMarker", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a marker created",
            httpMethod = "POST",
            response = MarkerResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about marker",
                    response = MarkerResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    public ResponseEntity<MarkerResponse> createMarker
            (
                    @ApiParam(value = "data of marker", required = true)
                    @Valid @RequestBody MarkerRequest request
            ) {
        MarkerResponse marker = markerService.createMarker(request);
        return new ResponseEntity<>(marker, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllMarkers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a list of markers",
            httpMethod = "GET",
            response = GenericListPaginationResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about marker",
                    response = GenericListPaginationResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<GenericListPaginationResponse<MarkerResponse>> getAllMarkers
            (
                    @ApiParam(value = "page to display", example = "1")
                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                    @ApiParam(value = "number of items per request", example = "1")
                    @RequestParam(name = "size", defaultValue = "5") Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws EmptyListException {

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteMarkerById/{id}")
    @ApiOperation(
            value = "Delete a marker by id",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "No Body Content"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Describes errors on invalid id which is not found.")
    })
    public ResponseEntity<Void> deleteMarkerById
            (
                    @ApiParam(name = "id", required = true, value = "Marker Id", example = "1")
                    @PathVariable("id") Long idMarker
            ) throws UserAccountNotFoundException {
        markerService.deleteMarkerById(idMarker);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
