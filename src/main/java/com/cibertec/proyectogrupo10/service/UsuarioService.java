package com.cibertec.proyectogrupo10.service;

import com.cibertec.proyectogrupo10.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    void guardarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(Integer id);
    void eliminarUsuario(Integer id);
}
