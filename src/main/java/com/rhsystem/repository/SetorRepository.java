package com.rhsystem.repository;

import com.rhsystem.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    // Aqui você pode adicionar métodos personalizados de consulta, se necessário
}