package br.com.cotiinformatica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Fornecedor;

//Exclusivo do springData
@Repository
public interface FornecedorRepository extends JpaRepository< Fornecedor, Integer>{
	

}
