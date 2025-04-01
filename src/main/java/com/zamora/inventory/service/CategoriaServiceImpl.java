package com.zamora.inventory.service;

import com.zamora.inventory.domain.Categoria;
import com.zamora.inventory.entity.CategoriaEntity;
import com.zamora.inventory.exceptions.ResourceNotFoundException;
import com.zamora.inventory.mappers.CategoriaMapper;
import com.zamora.inventory.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;


    @Override
    public List<Categoria> getCategoria() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        CategoriaEntity entity = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        return categoriaMapper.toDomain(entity);
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        CategoriaEntity entity = categoriaMapper.toEntity(categoria);
        CategoriaEntity savedEntity = categoriaRepository.save(entity);
        return categoriaMapper.toDomain(savedEntity);
    }

    @Override
    public Categoria updateCategoria(Long id, Categoria categoria) {
        CategoriaEntity entityExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        entityExistente.setNombre(categoria.getNombre());

        CategoriaEntity updateEntity = categoriaRepository.save(entityExistente);
        return categoriaMapper.toDomain(updateEntity);
    }

    @Override
    public void deleteCategoria(Long id) {
        CategoriaEntity entity = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        categoriaRepository.delete(entity);
    }
}
