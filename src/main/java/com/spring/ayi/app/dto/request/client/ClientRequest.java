package com.spring.ayi.app.dto.request.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must be a word beginning with capital case.")
    @ApiModelProperty(position = 1, required = true, notes = "Non empty value, The first name is required.")
    private String name;

    @NotNull(message = "Lastname can not be null.")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Lastname must be a word beginning with capital case.")
    @ApiModelProperty(position = 2, required = true, notes = "Non empty value, The last name is required.")
    private String lastname;

    @NotNull(message = "Document number can not be null.")
    @Size(min = 7, max = 8, message = "Document number min size is 7 and max size is 8")
    @Pattern(regexp = "^\\d*$", message = "Document number must be numbers")
    @ApiModelProperty(position = 3, required = true, notes = "Non negative value, The document number is required.")
    private String documentNumber;

    @NotNull(message = "Client detail can not be null.")
    @ApiModelProperty(position = 4, required = true, notes = "Non empty value, The client detail is required.")
    private ClientDetailOnClientRequest clientDetail;

    @NotEmpty(message = "Address can not be null.")
    @ApiModelProperty(position = 5, required = true, notes = "Non empty value, The address is required.")
    @JsonIgnoreProperties(value = "clientDocumentNumber")
    private List<AddressRequestWithoutDocumentNumber> addresses;
}
