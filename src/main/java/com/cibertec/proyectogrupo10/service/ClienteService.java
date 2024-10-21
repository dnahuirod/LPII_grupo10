package com.cibertec.proyectogrupo10.service;

import com.cibertec.proyectogrupo10.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    void guardarCliente(Cliente cliente);
    Cliente obtenerClientePorId(Integer id);
    void eliminarCliente(Integer id);
}
