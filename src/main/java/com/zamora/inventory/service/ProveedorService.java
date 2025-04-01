package com.zamora.inventory.service;

import com.zamora.inventory.domain.Proveedor;

import java.util.List;

public interface ProveedorService {

    List<Proveedor> getProveedores();

    Proveedor getProveedorById(Long id);

    Proveedor saveProveedor(Proveedor proveedor);

    Proveedor updateProveedor(Long id, Proveedor proveedor);

    void deleteProveedor(Long id);

}
