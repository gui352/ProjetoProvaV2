package br.com.ProjetoAPI.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PessoaInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    private PessoaLoginInputDTO usuario;

}
