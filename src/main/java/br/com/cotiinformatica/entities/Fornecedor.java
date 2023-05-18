package br.com.cotiinformatica.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//ANOTAÇÕES do SPRING DATA
@Entity
@Table(name = "fornecedor") // informa o nome da tabela que será criada
//inclua as anotações dentro do metodo @Id / @GeneratedValue /@Column

//ANOTAÇÕES do LOMBOK
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // SINALIZA QUE É AUTO INCREMENT
	@Column(name = "idFornecedor")
	private Integer idFornecedor;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "cnpj", length = 20, nullable = false)
	private String cnpj;

	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;

	@OneToMany(mappedBy = "fornecedor")
	private List<Produto> produtos;

}
