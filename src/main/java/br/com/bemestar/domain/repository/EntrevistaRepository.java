package br.com.bemestar.domain.repository;

import br.com.bemestar.domain.entity.Entrevista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EntrevistaRepository extends JpaRepository <Entrevista, Long>{
}
