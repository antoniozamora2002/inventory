package com.zamora.inventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity(name = "producto")
@Data
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es requerido")
    private Double precio;

    @NotNull(message = "La cantidad es requerida")
    private Integer cantidadEnStock;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull(message = "La categoría es requerida")
    private CategoriaEntity categoriaId;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    @NotNull(message = "El proveedor es requerido")
    private ProveedorEntity proveedorId;

}
