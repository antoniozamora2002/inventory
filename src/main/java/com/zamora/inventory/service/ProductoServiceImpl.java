package com.zamora.inventory.service;

import com.zamora.inventory.domain.Producto;
import com.zamora.inventory.entity.CategoriaEntity;
import com.zamora.inventory.entity.ProductoEntity;
import com.zamora.inventory.entity.ProveedorEntity;
import com.zamora.inventory.exceptions.ResourceNotFoundException;
import com.zamora.inventory.mappers.ProductoMapper;
import com.zamora.inventory.repository.CategoriaRepository;
import com.zamora.inventory.repository.ProductoRepository;
import com.zamora.inventory.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Producto getProductoById(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return productoMapper.toDomain(entity);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        // Convertimos a entidad
        ProductoEntity entity = productoMapper.toEntity(producto);

        // Validar y asociar relaciones basadas en IDs
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            CategoriaEntity categoria = categoriaRepository.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + producto.getCategoria().getId()));
            entity.setCategoriaId(categoria);
        }

        if (producto.getProveedor() != null && producto.getProveedor().getId() != null) {
            ProveedorEntity proveedor = proveedorRepository.findById(producto.getProveedor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + producto.getProveedor().getId()));
            entity.setProveedorId(proveedor);
        }

        ProductoEntity savedEntity = productoRepository.save(entity);
        return productoMapper.toDomain(savedEntity);
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        ProductoEntity entityExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        // Actualizamos los datos básicos
        entityExistente.setNombre(producto.getNombre());
        entityExistente.setPrecio(producto.getPrecio());
        entityExistente.setCantidadEnStock(producto.getCantidadEnStock());

        if (producto.getDescripcion() != null) {
            entityExistente.setDescripcion(producto.getDescripcion());
        }

        // Actualizamos las relaciones si los IDs están presentes
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            CategoriaEntity categoria = categoriaRepository.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + producto.getCategoria().getId()));
            entityExistente.setCategoriaId(categoria);
        }

        if (producto.getProveedor() != null && producto.getProveedor().getId() != null) {
            ProveedorEntity proveedor = proveedorRepository.findById(producto.getProveedor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + producto.getProveedor().getId()));
            entityExistente.setProveedorId(proveedor);
        }

        ProductoEntity updatedEntity = productoRepository.save(entityExistente);
        return productoMapper.toDomain(updatedEntity);
    }

    @Override
    public void deleteProducto(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        productoRepository.delete(entity);
    }

}
