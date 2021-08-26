package br.com.ProjetoAPI.api.controller;

import br.com.ProjetoAPI.api.assembler.RolePessoaAssembler;
import br.com.ProjetoAPI.api.model.RolePessoaDTO;
import br.com.ProjetoAPI.api.model.input.RolePessoaInputDTO;
import br.com.ProjetoAPI.domain.model.RolePessoa;
import br.com.ProjetoAPI.domain.repository.RolePessoaRepository;
import br.com.ProjetoAPI.domain.service.RolePessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RolePessoaController {

    RolePessoaAssembler rolePessoaAssembler;
    RolePessoaRepository rolePessoaRepository;
    RolePessoaService rolePessoaService;

    @PostMapping
    public RolePessoaDTO cadastrar(@Valid @RequestBody RolePessoaInputDTO rolePessoaInputDTO){
        RolePessoa novaRole = rolePessoaAssembler.toEntity(rolePessoaInputDTO);
        RolePessoa rolePessoa = rolePessoaService.cadastrar(novaRole);
        return rolePessoaAssembler.toModel(rolePessoa);
    }

    @GetMapping
    public List<RolePessoaDTO> listar() {
        return rolePessoaService.listar();
    }

    @GetMapping("/buscar/{roleId}")
    public ResponseEntity<RolePessoaDTO> buscarPorId(@PathVariable Long roleId) {
        return rolePessoaService.buscarId(roleId);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RolePessoaDTO> editar(@Valid @PathVariable Long roleId, @RequestBody RolePessoaInputDTO rolePessoaInputDTO) {
        RolePessoa rolePessoa1 = rolePessoaAssembler.toEntity(rolePessoaInputDTO);
        rolePessoa1.setId(rolePessoa1.getId());
        rolePessoa1.setPessoas_codigo(rolePessoa1.getPessoas_codigo());
        rolePessoaService.editar(roleId, rolePessoa1);
        return ResponseEntity.ok(rolePessoaAssembler.toModel(rolePessoa1));
    }
}
