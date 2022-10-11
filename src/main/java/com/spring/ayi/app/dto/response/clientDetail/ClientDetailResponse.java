package com.spring.ayi.app.dto.response.clientDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.ayi.app.dto.response.client.ClientResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(
        value = "ClientDetailResponse",
        description = "Response of client detail."
)
public class ClientDetailResponse implements Serializable {

    @ApiModelProperty(position = 1, notes = "The client detail id.")
    private Long idClientDetail;

    @ApiModelProperty(position = 2, notes = "Category of client(vip or not vip)")
    private Boolean prime;

    @ApiModelProperty(position = 3, notes = "Acumulated points of the client.")
    private Long acumulatedPoints;

    @ApiModelProperty(position = 4, notes = "Client who belongs the details.")
    @JsonIgnoreProperties(value = "clientDetail")
    private ClientResponse client;

    @ApiModelProperty(position = 5, notes = "Soft delete.")
    private Boolean softDelete;
}
