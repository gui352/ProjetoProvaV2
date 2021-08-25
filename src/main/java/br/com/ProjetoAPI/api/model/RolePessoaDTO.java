package br.com.ProjetoAPI.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePessoaDTO {

    private Long id;

    private Long usuarios_id;

    private  String role_nome_role;

}
