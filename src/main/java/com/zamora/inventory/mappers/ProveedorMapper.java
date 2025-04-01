package com.zamora.inventory.mappers;

import com.zamora.inventory.domain.Proveedor;
import com.zamora.inventory.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public Proveedor toDomain(ProveedorEntity entity){
        if (entity == null) return null;

        return new Proveedor(
                entity.getId(),
                entity.getNombre(),
                entity.getTelefono(),
                entity.getCorreo()
        );
    }

    public ProveedorEntity toEntity(Proveedor domain) {
        if (domain == null) return null;

        ProveedorEntity entity = new ProveedorEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setTelefono(domain.getTelefono());
        entity.setCorreo(domain.getCorreo());
        return entity;
    }

}
