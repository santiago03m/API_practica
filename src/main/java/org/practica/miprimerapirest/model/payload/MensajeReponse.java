package org.practica.miprimerapirest.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class MensajeReponse implements Serializable {
    private String mensaje;

    private Object objeto;
}