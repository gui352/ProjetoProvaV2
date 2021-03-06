package br.com.ProjetoAPI.api.assembler;

import br.com.ProjetoAPI.api.model.PessoaDTO;
import br.com.ProjetoAPI.api.model.input.PessoaInputDTO;
import br.com.ProjetoAPI.domain.model.Pessoa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PessoaAssembler {

    ModelMapper modelMapper;

    public PessoaDTO toModel(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public Pessoa toEntity(PessoaInputDTO pessoaInputDTO){
        return modelMapper.map(pessoaInputDTO, Pessoa.class);
    }

    public List<PessoaDTO> toCollectionModel(List<Pessoa> pessoas){
        return pessoas.stream().map(this::toModel)
                .collect(Collectors.toList());
    }

}
