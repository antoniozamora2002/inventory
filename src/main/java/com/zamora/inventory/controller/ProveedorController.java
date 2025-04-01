package com.zamora.inventory.controller;

import com.zamora.inventory.domain.Proveedor;
import com.zamora.inventory.entity.ProveedorEntity;
import com.zamora.inventory.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    @Lazy
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorService.getProveedores();
    }

    @GetMapping("/{id}")
    public Proveedor obtenerProveedor(@PathVariable Long id) {
        return proveedorService.getProveedorById(id);
    }

    @PostMapping
    public Proveedor agregarProveedor(@Valid @RequestBody Proveedor proveedor) {
        return proveedorService.saveProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor actualizarProveedor(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor) {
        return proveedorService.updateProveedor(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }

}
