package com.rhsystem.repository;

import com.rhsystem.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Long> {
    List<Vacante> findByPreenchida(boolean preenchida);
    List<Vacante> findBySetorId(Long setId);
    List<Vacante> findByTituloContaining(String titulo);
}