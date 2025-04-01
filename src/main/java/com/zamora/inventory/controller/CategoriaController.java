package com.zamora.inventory.controller;


import com.zamora.inventory.domain.Categoria;
import com.zamora.inventory.entity.CategoriaEntity;
import com.zamora.inventory.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    @Lazy
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias(){
        return categoriaService.getCategoria();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoria(@PathVariable Long id){
        return categoriaService.getCategoriaById(id);
    }

    @PostMapping
    public Categoria agregarCategoria(@Valid @RequestBody Categoria categoria){
        return categoriaService.saveCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
        return categoriaService.updateCategoria(id, categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
