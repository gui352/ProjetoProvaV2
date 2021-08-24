package br.com.ProjetoAPI.domain.repository;

import br.com.ProjetoAPI.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

     @Query("SELECT p FROM Pessoa p WHERE p.email = ?1")
     Pessoa findByEmail(String email);
}
