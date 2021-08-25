package br.com.ProjetoAPI.domain.repository;

import br.com.ProjetoAPI.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

        @Query("select p from Produto p where p.produto = ?1")
        Optional<Produto> findByName(String produto);
}
