package com.zamora.inventory.service;

import com.zamora.inventory.domain.Categoria;
import com.zamora.inventory.entity.CategoriaEntity;

import java.util.List;

public interface CategoriaService {

    List<Categoria> getCategoria();

    Categoria getCategoriaById(Long id);

    Categoria saveCategoria(Categoria categoria);

    Categoria updateCategoria(Long id, Categoria categoria);

    void deleteCategoria(Long id);

}
