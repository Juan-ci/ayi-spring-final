package com.spring.ayi.app.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(
        value = "ClientRequest",
        description = "Represents the data needed to create Clients"
)
public class ClientRequest implements Serializable {

    /**
     * Basic data needed to create a client,
     * it is not allowed to add invoices when
     * you are creating the client
     */

    @NotNull(message = "Name can not be null.")
    @ApiModelProperty(position = 1, required = true, notes = "Non empty value, The first name is required.")
    private String name;

    @NotNull(message = "Lastname can not be null.")
    @ApiModelProperty(position = 2, required = true, notes = "Non empty value, The last name is required.")
    private String lastname;

    @NotNull(message = "Document number can not be null.")
    @ApiModelProperty(position = 3, required = true, notes = "Non negative value, The document number is required.")
    private String documentNumber;

    @NotNull(message = "Client detail can not be null.")
    @ApiModelProperty(position = 4, required = true, notes = "Non empty value, The client detail is required.")
    @JsonIgnoreProperties(value = "clientDocumentNumber")
    private ClientDetailRequest clientDetail;

    @NotNull(message = "Address can not be null.")
    @NotBlank(message = "Address can not be blank.")
    @ApiModelProperty(position = 5, required = true, notes = "Non empty value, The address is required.")
    @JsonIgnoreProperties(value = "clientDocumentNumber")
    private List<AddressRequest> addresses;
}
