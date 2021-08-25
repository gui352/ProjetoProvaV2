package br.com.ProjetoAPI.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private Integer codigo;

    private String nome;

    private PessoaLoginDTO pessoaLoginDTO;
}
