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

import br.com.cotiinformatica.dtos.FornecedorPostDTO;
import br.com.cotiinformatica.dtos.FornecedorPutDTO;
import br.com.cotiinformatica.entities.Fornecedor;
import br.com.cotiinformatica.repositories.FornecedorRepository;

@RestController // Annotation que classifica como um controller de API != de MVC que era
				// @controller
@RequestMapping(value = "/api/fornecedor")
public class FornecedoresController {

	@Autowired // autoinicialização
	private FornecedorRepository fornecedorRepository;

	// 1- cria o dto
	// 2- Inicia o dto
	// 3- "mapeia" o @RequestBody do SpringBoot
	@PostMapping
	public ResponseEntity<String> post(@RequestBody FornecedorPostDTO dto) {
		try {
			Fornecedor fornecedor = new Fornecedor();

			fornecedor.setNome(dto.getNome());
			fornecedor.setCnpj(dto.getCnpj());
			fornecedor.setTelefone(dto.getTelefone());

			fornecedorRepository.save(fornecedor);
			return ResponseEntity.status(201).body("Fornecedor cadastrado com sucesso.");

		} catch (Exception e) {

			return ResponseEntity.status(500).body(e.getMessage());

		}

	}

	@PutMapping
	public ResponseEntity<String> put(@RequestBody FornecedorPutDTO dto) {

		try {
			// CONSULTAR O FORNECEDOR ATRAVES DO ID
			Optional<Fornecedor> optional = fornecedorRepository.findById(dto.getIdFornecedor());

			// VERIFICAR SE O FORNECEDOR FOI ENCONTRADO
			if (optional.isPresent()) {
				// Se quiser a pergunta pode ser optional.isEmpity = "Se não existir o
				// idFornecedor retorna" o que colocamos no else
				// CAPTURAR OS DADOS DO FORNECEDOR
				Fornecedor fornecedor = optional.get();

				// MODIFICANDO OS DADOS DO FORNECEDOR
				fornecedor.setNome(dto.getNome());
				fornecedor.setCnpj(dto.getCnpj());
				fornecedor.setTelefone(dto.getTelefone());

				// ATUALIZANDO O BANCO DE DADOS
				fornecedorRepository.save(fornecedor);

				// HTTP 200 - OK

				return ResponseEntity.status(200).body("Alteração realizada com sucesso");
			} else {
				return ResponseEntity.status(404).body("Fornecedor não encontrado, verifique o ID informado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{idFornecedor}")
	public ResponseEntity<String> delete(@PathVariable("idFornecedor") Integer idFornecedor) {
		try {
			// BUSCAR O FORNECEDOR ATRAVES DO "OPTIONAL" DO SPRING DATA.
			// TEMPO DA AULA: 23/05 AS 10:30
			Optional<Fornecedor> optional = fornecedorRepository.findById(idFornecedor);
			if (optional.isPresent()) {

				Fornecedor fornecedor = optional.get();

				fornecedorRepository.delete(fornecedor);

				return ResponseEntity.status(200).body("Fornecedor excluido com sucesso!");

			} else {
				return ResponseEntity.status(400).body("Fornecedor não encontrado, verifique o Id do fornecedor");
			}

		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity<List<Fornecedor>> getAll() {
		try {
			// Consultar e retornar a lista de fornecedores com seus respectivos dados
			List<Fornecedor> fornecedores = fornecedorRepository.findAll();
			// HTTP 200 - OK
			return ResponseEntity.status(200).body(fornecedores);

		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}

	}

	// TODO: REVER A AULA DIA 23/05 AS 10:45
	@GetMapping("{idFornecedor}")
	public ResponseEntity<Fornecedor> getById(@PathVariable("idFornecedor") Integer idFornecedor) {

		try {
			// consultar o fornecedor através do ID
			Optional<Fornecedor> optional = fornecedorRepository.findById(idFornecedor);

			// verificando se o fornecedor foi encontrado
			if (optional.isPresent()) {
				// obter o fornecedor
				Fornecedor fornecedor = optional.get();
				// HTTP 200 - OK
				return ResponseEntity.status(200).body(fornecedor);
			} else {
				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);
			}
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}
}
