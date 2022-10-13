package com.spring.ayi.app.dto.request.userAccount;

import com.spring.ayi.app.dto.request.client.ClientRequestWithoutMailAccount;
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
        value = "UserAccountRequest",
        description = "Represents the data needed to create User accounts"
)
public class UserAccountRequest implements Serializable {

    @NotNull(message = "Mail can not be null.")
    @Email(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email format must be valid")
    @ApiModelProperty(position = 1, required = true, notes = "Mail of the user")
    private String mail;

    @NotNull(message = "Password can not be null")
    @Size(min = 8, message = "Min size is 8 characters.")
    @ApiModelProperty(position = 2, notes = "Password of the account")
    private String password;

    @ApiModelProperty(position = 3, notes = "Client owner of the user account. Could be null")
    private ClientRequestWithoutMailAccount client;
}
