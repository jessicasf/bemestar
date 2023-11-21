package br.com.bemestar.domain.controller;

import br.com.bemestar.domain.dto.ExercicioDTO;
import br.com.bemestar.domain.dto.TreinoDTO;
import br.com.bemestar.domain.entity.Exercicio;
import br.com.bemestar.domain.entity.Treino;
import br.com.bemestar.domain.repository.ExercicioRepository;
import br.com.bemestar.domain.repository.TreinoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/treino")
public class TreinoController {

    @Autowired
    private TreinoRepository repository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@Validated(TreinoDTO.OnCreate.class) @RequestBody TreinoDTO treinoDTO) {
        try {
            Treino treino = TreinoDTO.toEntity(treinoDTO, exercicioRepository);
            repository.save(treino);
            return ResponseEntity.ok().body(TreinoDTO.toDTO(treino));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@PageableDefault(size = 5) Pageable pageable) {
        Page<Treino> treinos = repository.findAll(pageable);
        return ResponseEntity.ok(treinos.getContent().stream().map(TreinoDTO::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return repository.findById(id)
                .map(TreinoDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TreinoDTO treinoDTO) {
        return repository.findById(id).map(treino -> {
            if (treinoDTO.getDescricao() != null) {
                treino.setDescricao(treinoDTO.getDescricao());
            }
            if (treinoDTO.getExerciciosIds() != null) {
                Set<Exercicio> exercicios = new HashSet<>();
                treinoDTO.getExerciciosIds().forEach(exercicioId -> {
                    Optional<Exercicio> exercicio = exercicioRepository.findById(exercicioId);
                    exercicio.ifPresent(exercicios::add);
                });
                treino.setExercicios(exercicios);
            }
            repository.save(treino);
            return ResponseEntity.ok().body(TreinoDTO.toDTO(treino));
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