package br.com.bemestar.domain.dto;

import br.com.bemestar.domain.entity.Profissional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfissionalDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class)
    private String nome;

    @Email(groups = OnCreate.class)
    private String email;

    @NotBlank(groups = OnCreate.class)
    private String telefone;

    @Size(max = 1000)
    private String imagem;

    public ProfissionalDTO(Long id, String nome, String email, String telefone, String imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imagem = imagem;
    }

    public ProfissionalDTO() {
    }

    public static Profissional toEntity(ProfissionalDTO dto) {
        return new Profissional(
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getImagem()
        );
    }

    public static ProfissionalDTO toDTO(Profissional profissional) {

        return new ProfissionalDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEmail(),
                profissional.getTelefone(),
                profissional.getImagem()
        );
    }

    public Long getId() {
        return id;
    }

    public ProfissionalDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ProfissionalDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfissionalDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public ProfissionalDTO setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getImagem() {
        return imagem;
    }

    public ProfissionalDTO setImagem(String imagem) {
        this.imagem = imagem;
        return this;
    }
}