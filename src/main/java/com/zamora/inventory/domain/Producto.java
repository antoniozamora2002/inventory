package com.zamora.inventory.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Long id;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer cantidadEnStock;

    private Categoria categoria;

    private Proveedor proveedor;

}
