package br.com.bemestar.domain.dto;

import br.com.bemestar.domain.entity.Entrevista;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EntrevistaDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotNull(groups = OnUpdate.class)
    private long id;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 2, max = 1000)
    private String link;

    @NotBlank(groups = OnCreate.class)
    private String descricao;


    public EntrevistaDTO(long id, String link, String descricao) {
        this.id = id;
        this.link = link;
        this.descricao = descricao;
    }

    public EntrevistaDTO() {
    }

    public static Entrevista toEntity(EntrevistaDTO dto) {
        return new Entrevista(
                dto.getId(),
                dto.getLink(),
                dto.getDescricao()
        );
    }

    public static EntrevistaDTO toDTO(Entrevista entrevista) {
        return new EntrevistaDTO(
                entrevista.getId(),
                entrevista.getLink(),
                entrevista.getDescricao()
        );
    }

    public long getId() {
        return id;
    }

    public EntrevistaDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getLink() {
        return link;
    }

    public EntrevistaDTO setLink(String link) {
        this.link = link;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public EntrevistaDTO setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}