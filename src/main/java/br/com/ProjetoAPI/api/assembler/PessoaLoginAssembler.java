package br.com.ProjetoAPI.api.assembler;

import br.com.ProjetoAPI.api.model.PessoaLoginDTO;
import br.com.ProjetoAPI.api.model.input.PessoaLoginInputDTO;
import br.com.ProjetoAPI.domain.model.Pessoa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PessoaLoginAssembler {

    ModelMapper modelMapper;

    public PessoaLoginDTO toModel(Pessoa pessoa){

        return modelMapper.map(pessoa, PessoaLoginDTO.class);
    }

    public Pessoa toEntity(PessoaLoginInputDTO pessoaLoginInputDTO){

        return modelMapper.map(pessoaLoginInputDTO, Pessoa.class);
    }
}
