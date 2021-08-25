package br.com.ProjetoAPI.api.controller;

import br.com.ProjetoAPI.api.assembler.ProdutoAssembler;
import br.com.ProjetoAPI.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoAssembler produtoAssembler;
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO cadastrar(@Valid @RequestBody ProdutoInputDTO produtoInputDTO){
        Produto novoProduto = produtoAssembler.toEntity(produtoInputDTO);
        novoProduto.setValor_total_em_estoque(produtoInputDTO.getValor_unitario()*produtoInputDTO.getQuantidade());
        Produto produto = produtoService.cadastrar(novoProduto);
        return produtoAssembler.toModel(produto);
    }

    @GetMapping("/listar")
    public List<ProdutoDTO> listar(){
        return produtoService.listar();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ProdutoDTO> editar(@Valid @PathVariable Long codigo,
                                             @RequestBody ProdutoInputDTO produtoInputDTO){
        Produto produto1 = produtoAssembler.toEntity(produtoInputDTO);
        produtoService.editar(codigo,produto1);
        return ResponseEntity.ok(produtoAssembler.toModel(produto1));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Produto> remover(@PathVariable Long codigo){
        if(!produtoRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }

}
