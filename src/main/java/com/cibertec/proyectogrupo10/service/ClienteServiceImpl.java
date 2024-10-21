package com.cibertec.proyectogrupo10.service;

import com.cibertec.proyectogrupo10.model.Cliente;
import com.cibertec.proyectogrupo10.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el cliente con id: " + id));
    }

    @Override
    public void eliminarCliente(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else{
            throw new RuntimeException("No se encontro el cliente con id: " + id);
        }
    }
}
