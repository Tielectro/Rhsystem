package com.rhsystem.controller;

import com.rhsystem.model.Setor;
import com.rhsystem.model.Vacante;
import com.rhsystem.repository.SetorRepository;
import com.rhsystem.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacantes")
public class VacanteController {

    @Autowired
    private VacanteRepository vacanteRepository;

    @Autowired
    private SetorRepository setorRepository; // Injeção do SetorRepository para acessar operações do Setor.


    @GetMapping
    public ResponseEntity<List<Vacante>> listarVacantes() {
        List<Vacante> vacantes = vacanteRepository.findAll();
        return ResponseEntity.ok(vacantes);
    }

    @PostMapping
    public ResponseEntity<?> criarVacante(@RequestBody Vacante vacante) {
        if (vacante.getSetor() == null || vacante.getSetor().getId() == null) {
            return ResponseEntity.badRequest().body("Setor não especificado. Por favor, selecione um setor para a vaga.");
        }

        Long setId = vacante.getSetor().getId();
        if (!setorRepository.existsById(setId)) {
            return ResponseEntity.badRequest().body("O setor selecionado não existe. Por favor, selecione um setor válido para a vaga.");
        }

        Vacante novaVacante = vacanteRepository.save(vacante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVacante);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Vacante> buscarVacantePorId(@PathVariable Long id) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante não encontrada com o ID: " + id));
        return ResponseEntity.ok(vacante);
    }

    @PutMapping("/atualizarvaga/{id}")
    public ResponseEntity<Vacante> atualizarVacante(@PathVariable Long id, @RequestBody Vacante vacanteAtualizada) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante não encontrada com o ID: " + id));

        // Atualizar apenas os campos fornecidos
        if (vacanteAtualizada.getTitulo() != null) {
            vacante.setTitulo(vacanteAtualizada.getTitulo());
        }
        if (vacanteAtualizada.getDescricao() != null) {
            vacante.setDescricao(vacanteAtualizada.getDescricao());
        }
        // Atualize outros campos conforme necessário

        Vacante vacanteAtualizadaBanco = vacanteRepository.save(vacante);
        return ResponseEntity.ok(vacanteAtualizadaBanco);
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Vacante>> buscarVacantesPorNome(@PathVariable String nome) {
        List<Vacante> vacantes = vacanteRepository.findByTituloContaining(nome);
        if (vacantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vacantes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVacante(@PathVariable Long id) {
        vacanteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/setor/{setId}")
    public ResponseEntity<List<Vacante>> listarVacantesPorSetor(@PathVariable Long setId) {
        List<Vacante> vacantes = vacanteRepository.findBySetorId(setId);
        return ResponseEntity.ok(vacantes);
    }

    // Criar um novo setor
    @PostMapping("/setor")
    public ResponseEntity<Setor> criarSetor(@RequestBody Setor setor) {
        Setor novoSetor = setorRepository.save(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSetor);
    }

    // Atualizar um setor existente
    @PutMapping("/setor/{id}")
    public ResponseEntity<Setor> atualizarSetor(@PathVariable Long id, @RequestBody Setor setorAtualizado) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com o ID: " + id));
        setor.setNome(setorAtualizado.getNome());
        setor.setDescricao(setorAtualizado.getDescricao());
        Setor setorAtualizadoBanco = setorRepository.save(setor);
        return ResponseEntity.ok(setorAtualizadoBanco);
    }

    // Obter detalhes de um setor por ID
    @GetMapping("/setor/{id}")
    public ResponseEntity<Setor> buscarSetorPorId(@PathVariable Long id) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com o ID: " + id));
        return ResponseEntity.ok(setor);
    }

    // Listar todos os setores
    @GetMapping("/setores")
    public ResponseEntity<List<Setor>> listarSetores() {
        List<Setor> setores = setorRepository.findAll();
        return ResponseEntity.ok(setores);
    }

    @PutMapping("/{id}/marcarPreenchida")
    public ResponseEntity<Vacante> marcarVagaComoPreenchida(@PathVariable Long id) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante não encontrada com o ID: " + id));

        vacante.setPreenchida(true);
        Vacante vacanteAtualizada = vacanteRepository.save(vacante);

        return ResponseEntity.ok(vacanteAtualizada);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Vacante>> listarVagasDisponiveis() {
        List<Vacante> vagasDisponiveis = vacanteRepository.findByPreenchida(false);
        return ResponseEntity.ok(vagasDisponiveis);
    }

    @GetMapping("/preenchidas")
    public ResponseEntity<List<Vacante>> listarVagasPreenchidas() {
        List<Vacante> vagasPreenchidas = vacanteRepository.findByPreenchida(true);
        return ResponseEntity.ok(vagasPreenchidas);
    }



}

