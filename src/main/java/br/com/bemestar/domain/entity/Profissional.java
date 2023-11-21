package br.com.bemestar.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BE_TB_PROFISSIONAL")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROFISSIONAL")
    private long id;

    @Column(name = "NM_PROFISSIONAL")
    private String nome;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "NR_TELEFONE")
    private String telefone;

    @Column(name = "LINK_IMAGEM", length = 1000)
    private String imagem;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Indicacao> indicacoes = new ArrayList<>();

    public Profissional(long id, String nome, String email, String telefone, String imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imagem = imagem;
    }

    public Profissional(String nome, String email, String telefone, String imagem) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imagem = imagem;
    }

    public Profissional() {
    }

    public long getId() {
        return id;
    }

    public Profissional setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Profissional setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public List<Indicacao> getIndicacoes() {
        return indicacoes;
    }

    public String getImagem() {
        return imagem;
    }

    public Profissional setImagem(String imagem) {
        this.imagem = imagem;
        return this;
    }

    public Profissional setIndicacoes(List<Indicacao> indicacoes) {
        this.indicacoes = indicacoes;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Profissional setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Profissional setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }
}
