package br.com.bemestar.domain.dto;

import br.com.bemestar.domain.entity.Exercicio;
import br.com.bemestar.domain.entity.Treino;
import br.com.bemestar.domain.repository.ExercicioRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TreinoDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 2, max = 1000)
    private String descricao;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Long> exerciciosIds;
    private Set<Exercicio> exercicios;

    public static Treino toEntity(TreinoDTO dto, ExercicioRepository exercicioRepository) {
        Set<Exercicio> exercicios = dto.getExerciciosIds().stream()
                .map(exercicioRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        return new Treino(
                dto.getId(),
                dto.getDescricao(),
                exercicios
        );
    }

    public TreinoDTO(Long id, String descricao, Set<Exercicio> exercicios) {
        this.id = id;
        this.descricao = descricao;
        this.exercicios = exercicios != null ? exercicios : new HashSet<>();
    }

    public static TreinoDTO toDTO(Treino treino) {
        return new TreinoDTO(
                treino.getId(),
                treino.getDescricao(),
                treino.getExercicios()
        );
    }

    public Long getId() {
        return id;
    }

    public TreinoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public TreinoDTO setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Set<Long> getExerciciosIds() {
        return exerciciosIds;
    }

    public TreinoDTO setExerciciosIds(Set<Long> exerciciosIds) {
        this.exerciciosIds = exerciciosIds;
        return this;
    }

    public Set<Exercicio> getExercicios() {
        return exercicios;
    }

    public TreinoDTO setExercicios(Set<Exercicio> exercicios) {
        this.exercicios = exercicios;
        return this;
    }
}