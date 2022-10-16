package com.spring.ayi.app.dto.request.marker;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(
        value = "MarkerRequest",
        description = "Represents the data needed to create a marker"
)
public class MarkerRequest implements Serializable {

    @ApiModelProperty(position = 1, required = true, notes = "Mail of the user")
    private String name;

    @ApiModelProperty(position = 2, notes = "Password of the account")
    private String lastname;

    @NotNull(message = "Longitude can not be null")
    @ApiModelProperty(position = 3, notes = "Longitude cords")
    private Float longitude;

    @NotNull(message = "Latitude can not be null")
    @ApiModelProperty(position = 4, notes = "Latitude cords")
    private Float latitude;
}
