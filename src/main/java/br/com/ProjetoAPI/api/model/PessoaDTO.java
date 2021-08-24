package br.com.ProjetoAPI.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String nome;
    private PessoaLoginDTO usuario;
    private String telefone;
}
