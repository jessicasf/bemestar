package br.com.bemestar.domain.controller;

import br.com.bemestar.domain.dto.UsuarioDTO;
import br.com.bemestar.domain.entity.Usuario;
import br.com.bemestar.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@Validated(UsuarioDTO.OnCreate.class) @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = UsuarioDTO.toEntity(usuarioDTO);
            repository.save(usuario);
            return ResponseEntity.ok().body(UsuarioDTO.toDTO(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@PageableDefault(size = 5) Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        return ResponseEntity.ok(usuarios.getContent().stream().map(UsuarioDTO::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return repository.findById(id)
                .map(UsuarioDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@Validated(UsuarioDTO.OnUpdate.class) @PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return repository.findById(id).map(usuario -> {
            if (usuarioDTO.getNome() != null) {
                usuario.setNome(usuarioDTO.getNome());
            }
            if (usuarioDTO.getEmail() != null) {
                usuario.setEmail(usuarioDTO.getEmail());
            }
            if (usuarioDTO.getSenha() != null) {
                usuario.setSenha(usuarioDTO.getSenha());
            }
            repository.save(usuario);
            return ResponseEntity.ok().body(UsuarioDTO.toDTO(usuario));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}