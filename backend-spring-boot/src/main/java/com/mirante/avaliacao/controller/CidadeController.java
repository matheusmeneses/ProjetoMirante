package com.mirante.avaliacao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.exception.ResourceNotFoundException;
import com.mirante.avaliacao.service.ProjetoService;

import lombok.AllArgsConstructor;
import lombok.Generated;

//--------------------------------------------------
/** Endpoint para consultar e manter cidades */
//--------------------------------------------------
@RestController
@Generated
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/cidades")
public class CidadeController {

	private final ProjetoService service ;
	
	//----------------------------------------------------------
	/** Endpoint que retorna todas as cidades cadastradas */
	//----------------------------------------------------------
	@RequestMapping(method = RequestMethod.GET)
	public List<CidadeDTO> pesquisarCidades() {
		// TODO: Responde GET em http://localhost:8080/mirante/cidades
		return service.pesquisarCidades();
	}
	
	//----------------------------------------------------------
	/** Endpoint para incluir nova cidade */
	//----------------------------------------------------------
	 @RequestMapping(method = RequestMethod.POST)
	public Long incluirCidade(@RequestBody(required = true) CidadeDTO cidade) {
		//	TODO: Responde POST em http://localhost:8080/mirante/cidades
		//	Envia JSON no body:
		//	{
		//	 	"nome": "Florianópolis",
		//	  	"uf": "SC",
		//	   	"capital": true
		//	}
		return service.incluirCidade(cidade);
	}	
	
	//----------------------------------------------------------
	/** Endpoint para alterar cidade */
	//----------------------------------------------------------
	 @RequestMapping(method = RequestMethod.PUT)
	public void alterarCidade(@RequestBody(required = true) CidadeDTO cidade) {
		 
		// TODO: Responde PUT em http://localhost:8080/mirante/cidades
		//   Envia JSON no body:
		//   {
		//     "id": 11,
		//     "nome": "Blumenau",
		//     "uf": "SC",
		//     "capital": false
		//   }
		try {
			service.alterarCidade(cidade); 
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	 
	//----------------------------------------------------------
	/** Endpoint para excluir uma cidade */
	//----------------------------------------------------------
	 @RequestMapping(value = "/{idCidade}"  , method = RequestMethod.DELETE)
	public void excluirCidade(@PathVariable(required = true) Long idCidade) {
		// Responde DELETE em http://localhost:8080/mirante/cidades/{idCidade}
		service.excluirCidade(idCidade);
	}	
}
