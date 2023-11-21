package br.com.bemestar.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BE_TB_ENTREVISTA")
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENTREVISTA")
    private long id;

    @Column(name = "LINK_ENTREVISTA", length = 1000)
    private String link;

    @Column(name = "DS_ENTREVISTA")
    private String descricao;

    public Entrevista(long id, String link, String descricao) {
        this.id = id;
        this.link = link;
        this.descricao = descricao;
    }

    public Entrevista() {
    }

    public long getId() {
        return id;
    }

    public Entrevista setId(long id) {
        this.id = id;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Entrevista setLink(String link) {
        this.link = link;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Entrevista setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}
