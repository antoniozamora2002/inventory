package com.zamora.inventory.mappers;

import com.zamora.inventory.domain.Categoria;
import com.zamora.inventory.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toDomain(CategoriaEntity entity){
        if (entity == null) return null;

        return new Categoria(
                entity.getId(),
                entity.getNombre()
        );

    }

    public CategoriaEntity toEntity(Categoria domain){
        if (domain == null) return null;

        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(entity.getId());
        entity.setNombre(entity.getNombre());
        return entity;

    }

}
