package br.com.bemestar.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BE_TB_EXERCICIO")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXERCICIO")
    private Long id;

    @Column(name = "DS_EXERCICIO")
    private String descricao;

    @Column(name = "LINK_VIDEO")
    private String linkVideo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_EXERCICIO")
    private TipoExercicio tipoExercicio;

    public Exercicio() {
    }

    public Exercicio(Long id, String descricao, String linkVideo, TipoExercicio tipoExercicio) {
        this.id = id;
        this.descricao = descricao;
        this.linkVideo = linkVideo;
        this.tipoExercicio = tipoExercicio;
    }

    public Long getId() {
        return id;
    }

    public Exercicio setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Exercicio setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public Exercicio setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
        return this;
    }

    public TipoExercicio getTipoExercicio() {
        return tipoExercicio;
    }

    public Exercicio setTipoExercicio(TipoExercicio tipoExercicio) {
        this.tipoExercicio = tipoExercicio;
        return this;
    }
}
