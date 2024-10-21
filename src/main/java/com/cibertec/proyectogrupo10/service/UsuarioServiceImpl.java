package com.cibertec.proyectogrupo10.service;

import com.cibertec.proyectogrupo10.model.Usuario;
import com.cibertec.proyectogrupo10.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el usuario con el id " + id))                ;
    }

    @Override
    public void eliminarUsuario(Integer id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontro el usuario con el id " + id);
        }
    }
}
