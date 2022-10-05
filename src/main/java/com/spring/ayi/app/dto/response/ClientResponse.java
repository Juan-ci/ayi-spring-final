package com.spring.ayi.app.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(
        value = "ClientResponse",
        description = "Represents the client data back from the server"
)
public class ClientResponse implements Serializable {

    @ApiModelProperty(position = 1, notes = "Client id.")
    private Long idClient;

    @ApiModelProperty(position = 2, notes = "The first name of the client.")
    private String name;

    @ApiModelProperty(position = 3, notes = "The last name of the client.")
    private String lastname;

    @ApiModelProperty(position = 4, notes = "The document number of the client.")
    private String documentNumber;

    @ApiModelProperty(position = 5, notes = "The client detail.")
    @JsonIgnoreProperties(value = "client")
    private ClientDetailResponse clientDetail;

    @JsonIgnoreProperties(value = "client")
    @ApiModelProperty(position = 6, notes = "The addresses of the client.")
    private List<AddressResponse> addresses;

    @ApiModelProperty(position = 7, notes = "The invoices of the client.")
    private List<AddressResponse> invoices;
}
