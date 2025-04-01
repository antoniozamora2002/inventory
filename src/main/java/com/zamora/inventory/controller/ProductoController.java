package com.zamora.inventory.controller;

import com.zamora.inventory.domain.Producto;
import com.zamora.inventory.entity.ProductoEntity;
import com.zamora.inventory.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    @Lazy
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.getProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @PostMapping
    public Producto agregarProducto(@Valid @RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        return productoService.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

}
