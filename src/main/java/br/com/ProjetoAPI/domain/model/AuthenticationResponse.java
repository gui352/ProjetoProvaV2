package br.com.ProjetoAPI.domain.model;

import br.com.ProjetoAPI.api.model.PessoaLoginDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private String jwt;
    private PessoaLoginDTO pessoaLoginDTO;

}
