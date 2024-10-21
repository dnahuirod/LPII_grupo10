package com.cibertec.proyectogrupo10.repository;

import com.cibertec.proyectogrupo10.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
}
