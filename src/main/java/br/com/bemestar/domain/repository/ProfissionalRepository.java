package br.com.bemestar.domain.repository;

import br.com.bemestar.domain.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
