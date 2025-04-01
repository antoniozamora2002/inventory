package com.zamora.inventory.service;

import com.zamora.inventory.domain.Proveedor;
import com.zamora.inventory.entity.ProveedorEntity;
import com.zamora.inventory.exceptions.ResourceNotFoundException;
import com.zamora.inventory.mappers.ProveedorMapper;
import com.zamora.inventory.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    @Override
    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll().stream()
                .map(proveedorMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Proveedor getProveedorById(Long id) {
        ProveedorEntity entity = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));
        return proveedorMapper.toDomain(entity);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        ProveedorEntity entity = proveedorMapper.toEntity(proveedor);
        ProveedorEntity savedEntity = proveedorRepository.save(entity);
        return proveedorMapper.toDomain(savedEntity);
    }

    @Override
    public Proveedor updateProveedor(Long id, Proveedor proveedor) {
        ProveedorEntity entityExistente = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        entityExistente.setNombre(proveedor.getNombre());
        entityExistente.setTelefono(proveedor.getTelefono());
        entityExistente.setCorreo(proveedor.getCorreo());

        ProveedorEntity updatedEntity = proveedorRepository.save(entityExistente);
        return proveedorMapper.toDomain(updatedEntity);
    }

    @Override
    public void deleteProveedor(Long id) {
        ProveedorEntity entity = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));
        proveedorRepository.delete(entity);
    }
}
