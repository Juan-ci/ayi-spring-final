package com.spring.ayi.app.dto.request;

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
public class ClientRequest implements Serializable {

    private static long serialVersionUID = 1L;

    private String name;

    private String lastname;

    private String documentNumber;

    //AGREGAR RELACION DETALLE CLIENTE

    //AGREGAR RELACION DIRECCIONES
}
