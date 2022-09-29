package com.spring.ayi.app.dto.response;

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
}
