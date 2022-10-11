package com.spring.ayi.app.dto.request.clientDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(
        value = "ClientDetailRequest",
        description = "Represents the data needed to create Clients details"
)
public class ClientDetailRequest implements Serializable {

    /**
     * You can create a client detail but it is not
     * allowed to create a new client, the client
     * must be created before
     */

    @NotNull(message = "Prime value can not be null.")
    @ApiModelProperty(position = 1, required = true, notes = "Boolean data")
    private Boolean prime;

    @NotNull(message = "Acumulated points can not be null")
    @ApiModelProperty(position = 2, notes = "Acumulated points of the client")
    private Long acumulatedPoints;

    @NotNull(message = "Client document number can not be null.")
    @Size(min = 7, message = "Client Document number min size is 7 and max size is 8")
    @Size(max = 8, message = "Client Document number min size is 7 and max size is 8")
    @ApiModelProperty(position = 3, required = true, notes = "Client owner of the details")
    private String clientDocumentNumber;
}
