package br.com.bemestar.domain.repository;

import br.com.bemestar.domain.entity.Indicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicacaoRepository extends JpaRepository<Indicacao, Long> {
}
