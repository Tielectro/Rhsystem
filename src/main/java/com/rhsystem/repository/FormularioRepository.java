package com.rhsystem.repository;


import com.rhsystem.model.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormularioRepository extends JpaRepository<Formulario, Long> {
    List<Formulario> findByNomeCompletoContainingIgnoreCase(String nome);

    // Método para buscar o último formulário registrado
    @Query("SELECT f FROM Formulario f ORDER BY f.id DESC")
    Formulario findUltimoFormulario();
}
