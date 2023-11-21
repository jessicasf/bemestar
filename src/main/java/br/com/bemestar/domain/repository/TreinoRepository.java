package br.com.bemestar.domain.repository;

import br.com.bemestar.domain.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
}
