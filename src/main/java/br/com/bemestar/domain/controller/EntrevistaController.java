package br.com.bemestar.domain.controller;


import br.com.bemestar.domain.dto.EntrevistaDTO;
import br.com.bemestar.domain.entity.Entrevista;
import br.com.bemestar.domain.repository.EntrevistaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/entrevista")
public class EntrevistaController {

    @Autowired
    private EntrevistaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@Validated(EntrevistaDTO.OnCreate.class) @RequestBody EntrevistaDTO entrevistaDTO) {
        try {
            Entrevista entrevista = EntrevistaDTO.toEntity(entrevistaDTO);
            repository.save(entrevista);
            return ResponseEntity.ok().body(EntrevistaDTO.toDTO(entrevista));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@PageableDefault(size = 5) Pageable pageable) {
        Page<Entrevista> entrevistas = repository.findAll(pageable);
        return ResponseEntity.ok(entrevistas.getContent().stream().map(EntrevistaDTO::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return repository.findById(id)
                .map(EntrevistaDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@Validated(EntrevistaDTO.OnUpdate.class) @PathVariable Long id, @RequestBody EntrevistaDTO entrevistaDTO) {
        return repository.findById(id).map(entrevista -> {
            if (entrevistaDTO.getLink() != null) {
                entrevista.setLink(entrevistaDTO.getLink());
            }
            if (entrevistaDTO.getDescricao() != null) {
                entrevista.setDescricao(entrevistaDTO.getDescricao());
            }
            repository.save(entrevista);
            return ResponseEntity.ok().body(EntrevistaDTO.toDTO(entrevista));
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