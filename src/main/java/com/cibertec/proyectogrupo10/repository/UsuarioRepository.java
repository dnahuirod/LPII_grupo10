package com.cibertec.proyectogrupo10.repository;

import com.cibertec.proyectogrupo10.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
