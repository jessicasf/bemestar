package br.com.bemestar.domain.dto;

import jakarta.validation.constraints.Email;

public class LoginDTO {

    @Email
    private String email;
    private String senha;

    public LoginDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public LoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public LoginDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public LoginDTO setSenha(String senha) {
        this.senha = senha;
        return this;
    }
}
