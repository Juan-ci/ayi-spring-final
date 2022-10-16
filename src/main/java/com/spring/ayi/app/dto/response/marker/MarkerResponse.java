package com.spring.ayi.app.dto.response.marker;

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
        value = "MarkerResponse",
        description = "Response of marker."
)
public class MarkerResponse implements Serializable {

    @ApiModelProperty(position = 1, notes = "The marker id.")
    private Long idMarker;

    @ApiModelProperty(position = 2, notes = "Name who sends the signal")
    private String name;

    @ApiModelProperty(position = 3, notes = "Lastname who sends the signal")
    private String lastname;

    @ApiModelProperty(position = 4, notes = "Longitude cords")
    private Float longitude;

    @ApiModelProperty(position = 5, notes = "Latitude cords")
    private Float latitude;

    @ApiModelProperty(position = 6, notes = "Soft delete.")
    private Boolean softDelete;
}
