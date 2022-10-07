package com.spring.ayi.app.dto.request.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(
        value = "ClientDetailOnClientRequest",
        description = "Represents the data needed to create Clients details when you are creating client."
)
public class ClientDetailOnClientRequest implements Serializable {

    @NotNull(message = "Prime value can not be null.")
    @ApiModelProperty(position = 1, required = true, notes = "Boolean data")
    private Boolean prime;

    @NotNull(message = "Acumulated points can not be null")
    @Pattern(regexp = "\\d", message = "Acumulated points must be digits from 0 to 9")
    @ApiModelProperty(position = 2, notes = "Acumulated points of the client")
    private Long acumulatedPoints;
}