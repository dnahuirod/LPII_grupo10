package com.cibertec.proyectogrupo10.repository;

import com.cibertec.proyectogrupo10.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
