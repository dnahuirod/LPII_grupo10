package com.cibertec.proyectogrupo10.service;

import com.cibertec.proyectogrupo10.model.Vendedor;

import java.util.List;

public interface VendedorService {
    List<Vendedor> listarVendedor();
    void guardarVendedor(Vendedor vendedor);
    Vendedor obtenerVendedorPorId(Integer id);
    void eliminarVendedor(Integer id);
}
