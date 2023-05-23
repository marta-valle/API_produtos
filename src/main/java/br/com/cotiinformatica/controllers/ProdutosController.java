package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ProdutoPostDTO;
import br.com.cotiinformatica.dtos.ProdutoPutDTO;
import br.com.cotiinformatica.entities.Fornecedor;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.FornecedorRepository;
import br.com.cotiinformatica.repositories.ProdutoRepository;

@RestController // Annotation que classifica como um controller de API != de MVC que era
				// @controller
@RequestMapping(value = "/api/produtos")
public class ProdutosController {

	// Inicializar: FornecedorRepository e ProdutosRepository, pq tem FK
	// LEMBRE-SE DE COLOCAR O @AUTOWIRED

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@PostMapping
	public ResponseEntity<String> post(@RequestBody ProdutoPostDTO dto) {
		try {
			// PESQUISAR SE O FORNECEDOR EXISTE
			Optional<Fornecedor> optional = fornecedorRepository.findById(dto.getIdFornecedor());

			if (optional.isPresent()) {
				// CAPTURAR O FORNECEDOR
				Fornecedor fornecedor = optional.get();

				// CRIANDO UM PRODUTO BASEADO NO DTO PARA SER GRAVADO NO BD
				// CRIA UMA CLASSE PRODUTO DO TIPO PRODUTO, RECEBENDO OS DADOS FORNECIDOS PELO
				// DTO.GET[...]
				Produto produto = new Produto();

				produto.setNome(dto.getNome());
				produto.setDescricao(dto.getDescricao());
				produto.setPreco(dto.getPreco());
				produto.setQuantidade(dto.getQuantidade());
				produto.setFornecedor(fornecedor);

				// SALVANDO PELO METODO DO SPRING DATA
				produtoRepository.save(produto);

				return ResponseEntity.status(201).body("Produto cadastrado com Sucesso!");

			} else {
				return ResponseEntity.status(404).body("Fornecedor não encontrado, verifique o Id do fornecedor");
			}

		} catch (Exception e) {

			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@PutMapping
	public ResponseEntity<String> put(@RequestBody ProdutoPutDTO dto) {

		try {
			// BUSCAR SE O PRODUTO EXISTE NO BD
			// BUSCAR SE O FORNECEDOR EXISTE NO BD
			// SE FOR ENCONTRATO TANTO NO PRODUTO QNT NO FORNECEDOR ; GRAVAR.
			// PODE SER OU NÃO IF COM &&
			Optional<Produto> produtoOptional = produtoRepository.findById(dto.getIdProduto());
			Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(dto.getIdFornecedor());

			if (produtoOptional.isPresent()) {
				if (fornecedorOptional.isPresent()) {
					Produto produto = produtoOptional.get();
					Fornecedor fornecedor = fornecedorOptional.get();

					produto.setNome(dto.getNome());
					produto.setDescricao(dto.getDescricao());
					produto.setPreco(dto.getPreco());
					produto.setQuantidade(dto.getQuantidade());
					produto.setFornecedor(fornecedor);

					// ATUALIZAR O PRODUTO NO BD
					produtoRepository.save(produto);

					return ResponseEntity.status(200).body("Atualizado com sucesso");
				} else {
					return ResponseEntity.status(404).body("Fornecedor não encontrado");
				}
			} else {
				return ResponseEntity.status(404).body("Produto não encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	@DeleteMapping("{idProduto}")
	public ResponseEntity<String> delete(@PathVariable("idProduto") Integer idProduto) {
		try {
			Optional<Produto> optional = produtoRepository.findById(idProduto);
			if (optional.isPresent()) {

				Produto produto = optional.get();

				// Importa o Método DELETE do SpringData
				produtoRepository.delete(produto);
				// Mensagem de retorno
				return ResponseEntity.status(200).body("Produto excluido com sucesso");
			} else {
				return ResponseEntity.status(404).body("Produto não encontrado, verifique o ID informado");
			}

		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	//TODO: falta ajustar todos os GET
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		try {
			List<Produto> produtos = produtoRepository.findAll();
			return ResponseEntity.status(200).body(produtos);

		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("{idProduto}")
	public ResponseEntity<Produto> getById(@PathVariable("idProduto") Integer idProduto){
		try {
			Optional<Produto> optional = produtoRepository.findById(idProduto);
			if(optional.isPresent()) {
				Produto produto = optional.get();
				return ResponseEntity.status(200).body(produto);
				}else {
					return ResponseEntity.status(204).body(null);
					}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
