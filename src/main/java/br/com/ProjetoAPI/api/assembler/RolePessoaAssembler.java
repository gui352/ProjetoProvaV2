package br.com.ProjetoAPI.api.assembler;

import br.com.ProjetoAPI.api.model.RolePessoaDTO;
import br.com.ProjetoAPI.api.model.input.RolePessoaInputDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RolePessoaAssembler {

    private ModelMapper modelMapper;
    private RoleUsuarioRepository roleUsuarioRepository;

    public RolePessoa toEntity(RolePessoaInputDTO rolePessoaInputDTO){
        return modelMapper.map(rolePessoaInputDTO, RolePessoa.class);
    }

    public List<RolePessoaDTO> toCollectionModel(List<RolePessoa> rolePessoas) {
        return rolePessoas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public RolePessoaDTO toModel(RolePessoa rolePessoa){
        return modelMapper.map(rolePessoa, RolePessoaDTO.class);
    }

}
