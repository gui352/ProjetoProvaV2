package br.com.ProjetoAPI.domain.service;

import br.com.ProjetoAPI.api.assembler.ProdutoAssembler;
import br.com.ProjetoAPI.api.model.ProdutoDTO;
import br.com.ProjetoAPI.domain.exception.NegocioException;
import br.com.ProjetoAPI.domain.model.Produto;
import br.com.ProjetoAPI.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private ProdutoAssembler produtoAssembler;

    @Transactional
    public Produto cadastrar(Produto produto){
        boolean productValidation = produtoRepository.findByName(
                produto.getProduto()).isPresent();
        if (!!productValidation){
            throw new NegocioException(
                    "Ja existe um produto com esse nome cadastrado.");
        }
        return produtoRepository.save(produto);
    }

    public Produto buscar(Long codigo){
        return produtoRepository.findById(codigo)
                .orElseThrow(() -> new NegocioException("Produto não encontrado."));
    }

    public List<ProdutoDTO> listar(){
        return produtoAssembler.toColletionModel(produtoRepository.findAll());
    }

    public ResponseEntity<ProdutoDTO> editar(Long codigo, Produto produto) {
        if(!produtoRepository.existsById(codigo)){
            throw new NegocioException("Produto inexistente");
        }
        Produto produto1 = this.buscar(codigo);
        produto.setCodigo(codigo);
        produto.setValor_total_em_estoque(produto.getValor_unitario()*produto.getQuantidade());
        produto1 = produtoRepository.save(produto);

        return ResponseEntity.ok(produtoAssembler.toModel(produto1));
    }

    @Transactional
    public void deletar(Long codigo){
        produtoRepository.deleteById(codigo);
    }
}
