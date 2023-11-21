package br.com.bemestar.domain.controller;

import br.com.bemestar.domain.dto.IndicacaoDTO;
import br.com.bemestar.domain.dto.ProfissionalDTO;
import br.com.bemestar.domain.entity.Indicacao;
import br.com.bemestar.domain.repository.IndicacaoRepository;
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
@RequestMapping("/indicacao")
public class IndicacaoController {

    @Autowired
    private IndicacaoRepository repository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@Validated(IndicacaoDTO.OnCreate.class) @RequestBody IndicacaoDTO indicacaoDTO) {
        try {
            var indicacao = IndicacaoDTO.toEntity(indicacaoDTO, profissionalRepository);
            repository.save(indicacao);
            return ResponseEntity.ok().body(IndicacaoDTO.toDTO(indicacao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@PageableDefault(size = 5) Pageable pageable) {
        Page<Indicacao> indicacoes = repository.findAll(pageable);
        return ResponseEntity.ok(indicacoes.getContent().stream().map(IndicacaoDTO::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return repository.findById(id)
                .map(IndicacaoDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@Validated(IndicacaoDTO.OnUpdate.class) @PathVariable Long id, @RequestBody IndicacaoDTO indicacaoDTO) {
        return repository.findById(id).map(indicacao -> {
            if (indicacaoDTO.getProfissional() != null) {
                indicacao.setProfissional(ProfissionalDTO.toEntity(indicacaoDTO.getProfissional()));
            }
            repository.save(indicacao);
            return ResponseEntity.ok().body(IndicacaoDTO.toDTO(indicacao));
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