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
public class AddressResponse implements Serializable {

    private final Long serialVersionUID = 1L;

    private Long idAddress;

    private String street;

    private String streetNumber;

    private Integer floor;

    private Integer postalCode;

    private String district;

    private String city;

    private String country;

    //AGREGAR RELACION CLIENTE
}
