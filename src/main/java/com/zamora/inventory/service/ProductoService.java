package com.zamora.inventory.service;

import com.zamora.inventory.domain.Producto;
import com.zamora.inventory.entity.ProductoEntity;

import java.util.List;

public interface ProductoService {

    List<Producto> getProductos();

    Producto getProductoById(Long id);

    Producto saveProducto(Producto producto);

    Producto updateProducto(Long id, Producto producto);

    void deleteProducto(Long id);

}
