package com.cibertec.proyectogrupo10.service;

import com.cibertec.proyectogrupo10.model.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> listarSucursal();
    void guardarSucursal(Sucursal sucursal);
    Sucursal obtenerSucursalPorId(Integer id);
    void eliminarSucursal(Integer id);
}
