package br.com.bemestar.domain.controller;

import br.com.bemestar.domain.dto.ExercicioDTO;
import br.com.bemestar.domain.entity.Exercicio;
import br.com.bemestar.domain.entity.TipoExercicio;
import br.com.bemestar.domain.repository.ExercicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@Validated(ExercicioDTO.OnCreate.class) @RequestBody ExercicioDTO exercicioDTO) {
        try {
            Exercicio exercicio = ExercicioDTO.toEntity(exercicioDTO);
            repository.save(exercicio);
            return ResponseEntity.ok().body(ExercicioDTO.toDTO(exercicio));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@PageableDefault(size = 5) Pageable pageable) {
        Page<Exercicio> exercicios = repository.findAll(pageable);
        return ResponseEntity.ok(exercicios.getContent().stream().map(ExercicioDTO::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return repository.findById(id)
                .map(ExercicioDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@Validated(ExercicioDTO.OnUpdate.class) @PathVariable Long id, @RequestBody ExercicioDTO exercicioDTO) {
        return repository.findById(id).map(exercicio -> {
            if (exercicioDTO.getDescricao() != null) {
                exercicio.setDescricao(exercicioDTO.getDescricao());
            }
            if (exercicioDTO.getLinkVideo() != null) {
                exercicio.setLinkVideo(exercicioDTO.getLinkVideo());
            }
            if (exercicioDTO.getTipoExercicio() != null) {
                exercicio.setTipoExercicio(TipoExercicio.valueOf(exercicioDTO.getTipoExercicio()));
            }
            repository.save(exercicio);
            return ResponseEntity.ok().build();
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