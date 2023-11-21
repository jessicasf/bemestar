package br.com.bemestar.domain.dto;

import br.com.bemestar.domain.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class)
    private String nome;

    @Email(groups = OnCreate.class)
    private String email;

    @NotBlank(groups = OnCreate.class)
    private String senha;

    public UsuarioDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioDTO() {
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        return new Usuario(
                dto.getId(),
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha()
        );
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }

    public Long getId() {
        return id;
    }

    public UsuarioDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public UsuarioDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsuarioDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioDTO setSenha(String senha) {
        this.senha = senha;
        return this;
    }
}