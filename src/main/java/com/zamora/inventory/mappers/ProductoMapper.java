package com.zamora.inventory.mappers;

import com.zamora.inventory.domain.Categoria;
import com.zamora.inventory.domain.Producto;
import com.zamora.inventory.domain.Proveedor;
import com.zamora.inventory.entity.CategoriaEntity;
import com.zamora.inventory.entity.ProductoEntity;
import com.zamora.inventory.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toDomain(ProductoEntity entity) {
        if (entity == null) return null;

        return new Producto(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getCantidadEnStock(),
                entity.getCategoriaId() != null
                        ? new Categoria(entity.getCategoriaId().getId(), entity.getCategoriaId().getNombre())
                        : null,
                entity.getProveedorId() != null
                        ? new Proveedor(entity.getProveedorId().getId(), entity.getProveedorId().getNombre(),
                        entity.getProveedorId().getTelefono(), entity.getProveedorId().getCorreo())
                        : null
        );
    }

    public ProductoEntity toEntity(Producto domain) {
        if (domain == null) return null;

        ProductoEntity entity = new ProductoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setPrecio(domain.getPrecio());
        entity.setCantidadEnStock(domain.getCantidadEnStock());

        if (domain.getCategoria() != null) {
            CategoriaEntity categoriaEntity = new CategoriaEntity();
            categoriaEntity.setId(domain.getCategoria().getId());
            entity.setCategoriaId(categoriaEntity);
        }

        if (domain.getProveedor() != null) {
            ProveedorEntity proveedorEntity = new ProveedorEntity();
            proveedorEntity.setId(domain.getProveedor().getId());
            entity.setProveedorId(proveedorEntity);
        }

        return entity;
    }

}
