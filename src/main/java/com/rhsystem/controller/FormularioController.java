package com.rhsystem.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.rhsystem.model.Formulario;
import com.rhsystem.repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/formulario")
public class FormularioController {

    @Autowired
    private FormularioRepository formularioRepository;

    // Endpoint para recuperar todos os formulários
    @GetMapping("/todos")
    public List<Formulario> buscarTodosFormularios() {
        return formularioRepository.findAll();
    }

    // Endpoint para recuperar um formulário pelo ID
    @GetMapping("/{id}")
    public Formulario buscarFormularioPorId(@PathVariable Long id) {
        return formularioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado com o id: " + id));
    }

    // Endpoint para salvar um novo formulário
    @PostMapping("/salvar")
    public Formulario salvarFormulario(@RequestBody Formulario formulario) {
        return formularioRepository.save(formulario);
    }

    // Novo endpoint para buscar formulários por nome
    @GetMapping("/nome/{nome}")
    public List<Formulario> buscarFormulariosPorNome(@PathVariable String nome) {
        return formularioRepository.findByNomeCompletoContainingIgnoreCase(nome);
    }

    @GetMapping("/ultimo")
    public Formulario buscarUltimoFormulario() {
        List<Formulario> formularios = formularioRepository.findAll();
        if (formularios.isEmpty()) {
            throw new RuntimeException("Nenhum formulário encontrado.");
        }
        return formularios.get(formularios.size() - 1);
    }
    // Endpoint para excluir um formulário pelo ID
    @DeleteMapping("/{id}/excluir")
    public String excluirFormulario(@PathVariable Long id) {
        formularioRepository.deleteById(id);
        return "Formulário excluído com sucesso.";
    }
    // Endpoint para imprimir um formulário em PDF
    @GetMapping("/{id}/imprimir")
    public ResponseEntity<byte[]> imprimirFormulario(@PathVariable Long id) {
        Optional<Formulario> optionalFormulario = formularioRepository.findById(id);
        if (optionalFormulario.isPresent()) {
            Formulario formulario = optionalFormulario.get();
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Document document = new Document();
                PdfWriter.getInstance(document, baos);

                document.open();

                Font font = FontFactory.getFont(FontFactory.HELVETICA, 12);
                Paragraph paragraph = new Paragraph("Detalhes do Formulário:\n\n", font);
                document.add(paragraph);

                // Adicione detalhes do formulário ao PDF
                document.add(new Paragraph("Nome Completo: " + formulario.getNomeCompleto(), font));
                document.add(new Paragraph("Endereço: " + formulario.getEndereco(), font));
                document.add(new Paragraph("Telefone: " + formulario.getTelefone(), font));
                document.add(new Paragraph("Descrição: " + formulario.getDescricao(), font));
                document.add(new Paragraph("Número Documento: " + formulario.getNumeroDocumento(), font));

                // Novos campos adicionados
                document.add(new Paragraph("Cursos: " + formulario.getCursos(), font));
                document.add(new Paragraph("Empresa: " + formulario.getEmpresa(), font));
                document.add(new Paragraph("Puesto: " + formulario.getPuesto(), font));
                document.add(new Paragraph("Tiempo de Permanencia: " + formulario.getTiempoPermanencia(), font));
                document.add(new Paragraph("Referencia: " + formulario.getReferencia(), font));
                document.add(new Paragraph("Disponibilidad: " + formulario.getDisponibilidad(), font));
                document.add(new Paragraph("Días Confirmar: " + formulario.getDiasConfirmar(), font));
                document.add(new Paragraph("Salario Pretendido: " + formulario.getSalarioPretendido(), font));

                document.close();

                // Converta o PDF para um array de bytes
                byte[] pdfBytes = baos.toByteArray();

                // Defina os cabeçalhos da resposta para indicar que é um PDF
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("filename", "formulario.pdf");

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } catch (DocumentException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
