package com.cibertec.proyectogrupo10.service;

import com.cibertec.proyectogrupo10.model.Sucursal;
import com.cibertec.proyectogrupo10.repository.SucursalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SucursalServiceImpl implements SucursalService {
    private final SucursalRepository sucursalRepository;
    @Override
    public List<Sucursal> listarSucursal() {
        return sucursalRepository.findAll();
    }

    @Override
    public void guardarSucursal(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal obtenerSucursalPorId(Integer id) {
        return sucursalRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el sucursal con id: " + id));
    }

    @Override
    public void eliminarSucursal(Integer id) {
        if(sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontro el sucursal con id: " + id);
        }
    }
}
