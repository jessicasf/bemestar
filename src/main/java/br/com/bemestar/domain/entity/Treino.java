package br.com.bemestar.domain.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "BE_TB_TREINO")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TREINO")
    private Long id;

    @Column(name = "DS_TREINO")
    private String descricao;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "BE_TB_TREINO_EXERCICIO",
            joinColumns = { @JoinColumn(name = "ID_TREINO", foreignKey = @ForeignKey(name = "FK_TREINO_EXERCICIO_TREINO")) },
            inverseJoinColumns = { @JoinColumn(name = "ID_EXERCICIO", foreignKey = @ForeignKey(name = "FK_TREINO_EXERCICIO_EXERCICIO")) }
    )
    private Set<Exercicio> exercicios = new LinkedHashSet<Exercicio>() {
    };

    public Treino() {
    }

    public Treino(Long id, String descricao, Set<Exercicio> exercicios) {
        this.id = id;
        this.descricao = descricao;
        this.exercicios = exercicios;
    }

    public Treino(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Treino addExercicio(Exercicio exercicio) {
        this.exercicios.add(exercicio);
        return this;
    }

    public Treino setExercicios(Set<Exercicio> exercicios) {
        this.exercicios = exercicios;
        return this;
    }

    public Treino removeExercicio(Exercicio exercicio) {
        this.exercicios.remove(exercicio);
        return this;
    }

    public Set<Exercicio> getExercicios() {
        return Collections.unmodifiableSet(this.exercicios);
    }

    public Long getId() {
        return id;
    }

    public Treino setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Treino setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}
