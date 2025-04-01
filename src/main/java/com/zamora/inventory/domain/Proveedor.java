package com.zamora.inventory.domain;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    private Long id;

    private String nombre;

    private String telefono;

    private String correo;

}
