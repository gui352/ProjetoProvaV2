package br.com.ProjetoAPI.api.controller;

import br.com.ProjetoAPI.api.assembler.PessoaAssembler;
import br.com.ProjetoAPI.api.model.PessoaDTO;
import br.com.ProjetoAPI.api.model.input.PessoaInputDTO;
import br.com.ProjetoAPI.domain.model.Pessoa;
import br.com.ProjetoAPI.domain.model.RolePessoa;
import br.com.ProjetoAPI.domain.repository.PessoaRepository;
import br.com.ProjetoAPI.domain.service.PessoaService;
import br.com.ProjetoAPI.domain.service.RolePessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;
    private RolePessoaService rolePessoaService;

    @PostMapping("/cadastrar")
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        novaPessoa.setSenha(new BCryptPasswordEncoder()
                .encode(pessoaInputDTO.getUsuario().getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);
        RolePessoa novaRole = new RolePessoa();
        novaRole.setPessoas_codigo(novaPessoa.getCodigo());
        novaRole.setRole_nome_role("ROLE_USER");
        rolePessoaService.cadastrar(novaRole);

        return pessoaAssembler.toModel(pessoa);
    }

    @GetMapping("/listar")
    public List<PessoaDTO> listar(){
        return pessoaService.listar();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long codigo,
                                            @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        pessoa.setSenha(new BCryptPasswordEncoder()
                .encode(pessoaInputDTO.getUsuario().getSenha()));
        pessoaService.editar(codigo,pessoa);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long codigo){
        if(!pessoaRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        pessoaService.excluir(codigo);
        return ResponseEntity.noContent().build();
    }
}
