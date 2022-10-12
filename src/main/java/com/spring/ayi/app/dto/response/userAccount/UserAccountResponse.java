package com.spring.ayi.app.dto.response.userAccount;

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
        value = "UserAccountResponse",
        description = "Response of user account."
)
public class UserAccountResponse implements Serializable {

    @ApiModelProperty(position = 1, notes = "The user account id.")
    private Long idUserAccount;

    @ApiModelProperty(position = 2, notes = "Mail of de user account")
    private String mail;

    @ApiModelProperty(position = 3, notes = "Data client of the account.")
    @JsonIgnoreProperties(value = "userAccount")
    private ClientResponse client;

    @ApiModelProperty(position = 4, notes = "Soft delete.")
    private Boolean softDelete;

    //Si puedo automatizar la creación de user account con pass hasheado, agregar pass
    //para mostrar user y pass ya creados
}
