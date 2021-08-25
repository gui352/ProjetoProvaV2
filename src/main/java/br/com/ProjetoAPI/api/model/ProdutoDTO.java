package br.com.ProjetoAPI.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long codigo;

    private String produto;

    private Long quantidade;

    private float valor_unitario;

    private float valor_total_em_estoque;
}
