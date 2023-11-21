package br.com.bemestar.domain.dto;

import br.com.bemestar.domain.entity.Exercicio;
import br.com.bemestar.domain.entity.TipoExercicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExercicioDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 2, max = 1000)
    private String linkVideo;

    @NotBlank(groups = OnCreate.class)
    private String descricao;

    @NotBlank(groups = OnCreate.class)
    private String tipoExercicio;

    public ExercicioDTO(Long id, String linkVideo, String descricao, String tipoExercicio) {
        this.id = id;
        this.linkVideo = linkVideo;
        this.descricao = descricao;
        this.tipoExercicio = tipoExercicio;
    }

    public ExercicioDTO() {
    }

    public static Exercicio toEntity(ExercicioDTO dto) {
        return new Exercicio(
                dto.getId(),
                dto.getDescricao(),
                dto.getLinkVideo(),
                TipoExercicio.valueOf(dto.getTipoExercicio())
        );
    }

    public static ExercicioDTO toDTO(Exercicio exercicio) {
        return new ExercicioDTO(
                exercicio.getId(),
                exercicio.getLinkVideo(),
                exercicio.getDescricao(),
                exercicio.getTipoExercicio().name()
        );
    }

    public Long getId() {
        return id;
    }

    public ExercicioDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public ExercicioDTO setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public ExercicioDTO setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getTipoExercicio() {
        return tipoExercicio;
    }

    public ExercicioDTO setTipoExercicio(String tipoExercicio) {
        this.tipoExercicio = tipoExercicio;
        return this;
    }
}