package br.com.ProjetoAPI.api.assembler;

import br.com.ProjetoAPI.api.model.ProdutoDTO;
import br.com.ProjetoAPI.api.model.input.ProdutoInputDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProdutoAssembler {

    private ModelMapper modelMapper;

    public ProdutoDTO toModel(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toColletionModel(List<Produto> produtos){
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Produto toEntity(ProdutoInputDTO produtoInputDTO){
        return modelMapper.map(produtoInputDTO, Produto.class);
    }
}
