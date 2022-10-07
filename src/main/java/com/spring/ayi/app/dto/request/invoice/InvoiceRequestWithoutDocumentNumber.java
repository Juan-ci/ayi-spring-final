package com.spring.ayi.app.dto.request.invoice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(
        value = "Invoice Request",
        description = "Represents invoice data"
)
public class InvoiceRequestWithoutDocumentNumber implements Serializable {

    @NotNull(message = "Description can not be null.")
    @ApiModelProperty(position = 1, required = true, notes = "Invoice description.")
    private String description;

    @NotNull(message = "Non negative value, total can not be null")
    @ApiModelProperty(position = 2, required = true, notes = "Non negative value, total can not be null")
    private Double total;
}