package com.spring.ayi.app.dto.request;

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
        value = "Address Request",
        description = "Represents address data"
)
public class AddressRequest implements Serializable {

    @ApiModelProperty(position = 1, required = true, notes = "Not null value, street is required")
    private String street;

    @ApiModelProperty(position = 2, required = true, notes = "Not null value, street number is required")
    private String streetNumber;

    @ApiModelProperty(position = 3, required = true, notes = "Not null value, floor is required")
    private Integer floor;

    @ApiModelProperty(position = 4, required = true, notes = "Not null value, postal code is required")
    private Integer postalCode;

    @ApiModelProperty(position = 5, required = true, notes = "Not null value, district is required")
    private String district;

    @ApiModelProperty(position = 6, required = true, notes = "Not null value, city is required")
    private String city;

    @ApiModelProperty(position = 7, required = true, notes = "Not null value, country is required")
    private String country;

    //AGREGAR RELACION CLIENTE
}
