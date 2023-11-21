package br.com.bemestar.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BE_TB_INDICACAO", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_PROFISSIONAL"}, name = "UK_INDICACAO_PROFISSIONAL")
})
public class Indicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INDICACAO")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROFISSIONAL", foreignKey = @ForeignKey(name = "FK_INDICACAO_PROFISSIONAL"))
    private Profissional profissional;

    public Indicacao() {
    }

    public Indicacao(Profissional profissional) {
        this.profissional = profissional;
    }

    public Indicacao(long id, Profissional profissional) {
        this.id = id;
        this.profissional = profissional;
    }

    public long getId() {
        return id;
    }

    public Indicacao setId(long id) {
        this.id = id;
        return this;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public Indicacao setProfissional(Profissional profissional) {
        this.profissional = profissional;
        return this;
    }
}
