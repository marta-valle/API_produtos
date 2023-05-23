package br.com.cotiinformatica.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProdutoPostDTO {

	private String nome;
	private Double preco;
	private Integer quantidade;
	private String descricao;	
	private Integer idFornecedor; //SERÁ FK
}
