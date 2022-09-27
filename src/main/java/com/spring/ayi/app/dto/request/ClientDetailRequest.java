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
public class ClientDetailRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long idClientDetail;

    private Boolean prime;

    private Long acumulatedPoints;

    //AGREGAR CLIENTE?
}
