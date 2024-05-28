package com.rhsystem.controller;


import com.rhsystem.model.Usuario;
import com.rhsystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint para autenticar um usuário
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // Verifique se o usuário existe no banco de dados
        Usuario user = usuarioRepository.findByUsername(usuario.getUsername());

        if (user != null && user.getPassword().equals(usuario.getPassword())) {
            // Usuário autenticado com sucesso
            return new ResponseEntity<>("Usuário autenticado com sucesso.", HttpStatus.OK);
        } else {
            // Credenciais inválidas
            return new ResponseEntity<>("Credenciais inválidas. Por favor, tente novamente.", HttpStatus.UNAUTHORIZED);
        }
    }

    // Endpoint para registrar um novo usuário
    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody Usuario usuario) {
        // Verifique se o nome de usuário já está em uso
        Usuario existingUser = usuarioRepository.findByUsername(usuario.getUsername());

        if (existingUser != null) {
            // Nome de usuário já está em uso
            return new ResponseEntity<>("Nome de usuário já está em uso. Por favor, escolha outro.", HttpStatus.BAD_REQUEST);
        }

        // Salve o novo usuário no banco de dados
        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuário registrado com sucesso.", HttpStatus.CREATED);
    }
}
