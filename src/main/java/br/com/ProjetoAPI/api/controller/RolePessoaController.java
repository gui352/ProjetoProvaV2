package br.com.ProjetoAPI.api.controller;

import br.com.ProjetoAPI.api.assembler.RolePessoaAssembler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RolePessoaController {

    RolePessoaAssembler rolePessoaAssembler;
    RoleUsuarioRepository roleUsuarioRepository;
    RoleUsuarioService roleUsuarioService;

    @PostMapping
    public RoleUsuarioDTO cadastrar(@Valid @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO){
        RolePessoa novaRole = rolePessoaAssembler.toEntity(roleUsuarioInputDTO);
        RolePessoa rolePessoa = roleUsuarioService.cadastrar(novaRole);
        return rolePessoaAssembler.toModel(rolePessoa);
    }

    @GetMapping
    public List<RoleUsuarioDTO> listar() {
        return roleUsuarioService.listar();
    }

    @GetMapping("/buscar/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> buscarPorId(@PathVariable Long roleId) {
        return roleUsuarioService.buscarId(roleId);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> editar(@Valid @PathVariable Long roleId, @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO) {
        RolePessoa rolePessoa1 = rolePessoaAssembler.toEntity(roleUsuarioInputDTO);
        roleUsuarioService.editar(roleId, rolePessoa1);
        return ResponseEntity.ok(rolePessoaAssembler.toModel(rolePessoa1));
    }
}
