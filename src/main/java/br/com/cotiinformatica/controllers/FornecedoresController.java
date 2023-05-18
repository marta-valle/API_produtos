package br.com.cotiinformatica.controllers;

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

	// TODO: POST
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

	// TODO: PUT
	@PutMapping
	public void put(@RequestBody FornecedorPutDTO dto) {

	}

	// TODO: DELETE
	@DeleteMapping("{idFornecedor}")
	public void delete(@PathVariable("idFornecedor") Integer idFornecedor) {

	}

	// TODO: GET ALL
	@GetMapping
	public void getAll() {

	}

	// TODO: GETById
	@GetMapping("{idFornecedor})")
	public void getById(@PathVariable("idFornecedor") Integer idFornecedor) {

	}
}
