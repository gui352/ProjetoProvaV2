package br.com.ProjetoAPI.domain.service;

import br.com.ProjetoAPI.api.assembler.RolePessoaAssembler;
import br.com.ProjetoAPI.api.model.RolePessoaDTO;
import br.com.ProjetoAPI.domain.exception.NegocioException;
import br.com.ProjetoAPI.domain.model.RolePessoa;
import br.com.ProjetoAPI.domain.repository.RolePessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RolePessoaService {

    private RolePessoaRepository rolePessoaRepository;
    private RolePessoaAssembler rolePessoaAssembler;

    @Transactional
    public RolePessoa cadastrar(RolePessoa rolePessoa){return rolePessoaRepository.save(rolePessoa);}

    public List<RolePessoaDTO> listar(){
        return rolePessoaAssembler.toCollectionModel(rolePessoaRepository.findAll());
    }

    public RolePessoa buscar(Long roleId){
        return rolePessoaRepository.findById(roleId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<RolePessoaDTO> buscarId(Long roleId){
        return rolePessoaRepository.findById(roleId)
                .map( roleUsuario ->
                        ResponseEntity.ok(rolePessoaAssembler.toModel(roleUsuario))
                )
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<RolePessoaDTO> editar(Long roleId, RolePessoa rolePessoa){
        if (!rolePessoaRepository.existsById(roleId)){
            throw new NegocioException("Pessoa inexistente.");
        }
        RolePessoa rolePessoa1 = this.buscar(roleId);
        rolePessoa.setId(roleId);
        rolePessoa.setPessoas_codigo(rolePessoa.getPessoas_codigo());
        rolePessoa = rolePessoaRepository.save(rolePessoa);
        return ResponseEntity.ok(rolePessoaAssembler.toModel(rolePessoa));
    }

}
