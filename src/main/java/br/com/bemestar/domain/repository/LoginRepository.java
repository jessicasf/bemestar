package br.com.bemestar.domain.repository;

import br.com.bemestar.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailAndSenha(String email, String senha);
    Usuario findByEmail(String email);

}
