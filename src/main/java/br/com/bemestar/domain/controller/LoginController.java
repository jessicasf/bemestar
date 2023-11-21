package br.com.bemestar.domain.controller;

import br.com.bemestar.domain.dto.LoginDTO;
import br.com.bemestar.domain.dto.UsuarioDTO;
import br.com.bemestar.domain.repository.LoginRepository;
import br.com.bemestar.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private LoginRepository repository;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        var usuario = repository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
        if (usuario != null) {
            return ResponseEntity.ok().body(usuario);
        } else {
            return ResponseEntity.status(401).body("Usuário não encontrado");
        }
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            var usuario = UsuarioDTO.toEntity(usuarioDTO);
            if (repository.findByEmail(usuario.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Usuário já cadastrado com este email.");
            }
            repository.save(usuario);
            return ResponseEntity.ok().body(UsuarioDTO.toDTO(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}