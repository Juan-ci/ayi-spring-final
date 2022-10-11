package com.spring.ayi.app.dto.response.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(
        value = "ErrorResponse",
        description = "Represents the error response"
)
public class ErrorResponse {

    @ApiModelProperty(position = 1, notes = "Http status name")
    private String status;

    @ApiModelProperty(position = 2, notes = "Http status code")
    private int code;

    @ApiModelProperty(position = 3, notes = "Message error")
    private String message;
}
