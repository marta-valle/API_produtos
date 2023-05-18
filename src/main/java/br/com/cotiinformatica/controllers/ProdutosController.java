package br.com.cotiinformatica.controllers;

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

@RestController //Annotation que classifica como um controller de API != de MVC que era @controller
@RequestMapping (value = "/api/produtos")
public class ProdutosController {
	
	//TODO: POST
	@PostMapping
	public void post(@RequestBody ProdutoPostDTO dto) {
		
	}

	//TODO: PUT
	@PutMapping
	public void put(@RequestBody ProdutoPutDTO dto) {
		
	}
	//TODO: DELETE
	@DeleteMapping ("{idProduto}")
	public void delete(@PathVariable ("idProduto") Integer idProduto) {
		
	}
	//TODO: GETALL
	@GetMapping
	public void getAll() {
		
	}
	//TODO: GETBy_ID
	
	@GetMapping ("{idProduto}")
	public void getById(@PathVariable("idProduto") Integer idProduto) {
		
	}

}
