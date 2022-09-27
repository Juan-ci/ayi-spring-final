package com.spring.ayi.app.dto.response;

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
public class ClientResoponse implements Serializable {

    private static long serialVersionUID = 1L;

    private Long idClient;

    private String name;

    private String lastname;

    private String documentNumber;

    //AGREGAR RELACION DETALLE CLIENTE

    //AGREGAR RELACION DIRECCIONES
}
