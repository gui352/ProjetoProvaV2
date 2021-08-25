package br.com.ProjetoAPI.domain.service;

import br.com.ProjetoAPI.api.assembler.PessoaAssembler;
import br.com.ProjetoAPI.api.model.PessoaDTO;
import br.com.ProjetoAPI.domain.exception.NegocioException;
import br.com.ProjetoAPI.domain.model.Pessoa;
import br.com.ProjetoAPI.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private PessoaAssembler pessoaAssembler;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa){
        boolean emailValidation = pessoaRepository.findByEmail(pessoa.getEmail())
                .isPresent();
        if(emailValidation){
            throw  new NegocioException("Já existe uma pessoa com este e-mail cadastrado.");
        }

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Long pessoaId){
        pessoaRepository.deleteById(pessoaId);
    }

    public Pessoa buscar(Long pessoaId){
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public List<PessoaDTO> listar(){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    public ResponseEntity<PessoaDTO> editar(Long codigo, Pessoa pessoa) {
        if (!pessoaRepository.existsById(codigo)){
            throw new NegocioException("Pessoa não encontrada");
        }
        Pessoa pessoa1 = this.buscar(codigo);
        pessoa.setCodigo(codigo);
        pessoa1 = pessoaRepository.save(pessoa);

        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa1));
    }

}
