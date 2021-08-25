package br.com.ProjetoAPI.domain.repository;

import br.com.ProjetoAPI.domain.model.RolePessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePessoaRepository extends JpaRepository<RolePessoa, Long> {
}
