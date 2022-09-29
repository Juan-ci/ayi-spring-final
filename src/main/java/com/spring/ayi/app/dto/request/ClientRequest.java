package com.spring.ayi.app.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        description = "Represents the data needed to creat Clients"
)
public class ClientRequest implements Serializable {

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
    private ClientDetailRequest clientDetail;

//    @NotNull(message = "Address can not be null.")
    @ApiModelProperty(position = 5, required = true, notes = "Non empty value, The address is required.")
    private List<AddressRequest> addresses;
}
