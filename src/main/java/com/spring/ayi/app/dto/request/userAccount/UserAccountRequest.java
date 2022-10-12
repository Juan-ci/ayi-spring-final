package com.spring.ayi.app.dto.request.userAccount;

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
        value = "UserAccountRequest",
        description = "Represents the data needed to create Clients details"
)
public class UserAccountRequest implements Serializable {

    @NotNull(message = "Mail can not be null.")
    @ApiModelProperty(position = 1, required = true, notes = "Boolean data")
    private String mail;

    @NotNull(message = "Password can not be null")
    //Agregar size
    @ApiModelProperty(position = 2, notes = "Password of the account")
    private String password;

    @NotNull(message = "Client document number can not be null.")
    @Size(min = 7, message = "Client Document number min size is 7 and max size is 8")
    @Size(max = 8, message = "Client Document number min size is 7 and max size is 8")
    @ApiModelProperty(position = 3, required = true, notes = "Client owner of the details")
    private String clientDocumentNumber;
}
