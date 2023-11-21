package br.com.bemestar.domain.controller;

import br.com.bemestar.domain.dto.ProfissionalDTO;
import br.com.bemestar.domain.entity.Profissional;
import br.com.bemestar.domain.repository.ProfissionalRepository;
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
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@Validated(ProfissionalDTO.OnCreate.class) @RequestBody ProfissionalDTO profissionalDTO) {
        try {
            Profissional profissional = ProfissionalDTO.toEntity(profissionalDTO);
            repository.save(profissional);
            return ResponseEntity.ok().body(ProfissionalDTO.toDTO(profissional));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@PageableDefault(size = 5) Pageable pageable) {
        Page<Profissional> profissionais = repository.findAll(pageable);
        return ResponseEntity.ok(profissionais.getContent().stream().map(ProfissionalDTO::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return repository.findById(id)
                .map(ProfissionalDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@Validated(ProfissionalDTO.OnUpdate.class) @PathVariable Long id, @RequestBody ProfissionalDTO profissionalDTO) {
        return repository.findById(id).map(profissional -> {
            if (profissionalDTO.getNome() != null) {
                profissional.setNome(profissionalDTO.getNome());
            }
            if (profissionalDTO.getEmail() != null) {
                profissional.setEmail(profissionalDTO.getEmail());
            }
            if (profissionalDTO.getTelefone() != null) {
                profissional.setTelefone(profissionalDTO.getTelefone());
            }
            if (profissionalDTO.getImagem() != null) {
                profissional.setImagem(profissionalDTO.getImagem());
            }
            repository.save(profissional);
            return ResponseEntity.ok().body(ProfissionalDTO.toDTO(profissional));
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