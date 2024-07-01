package com.mirante.avaliacao.dto;

import com.mirante.avaliacao.model.Cidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//-------------------------------------------------
/** DTO que guarda os dados de uma cidade */
//-------------------------------------------------
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {

	//---------------------------------------
	// Atributos do DTO
	//---------------------------------------
    private Long id;
    private String nome;
    private String uf;
    private Boolean capital;
    
    
    //-----------------------------------------------
    /** Carrega o DTO com dados da entidade */
    //-----------------------------------------------
    public static CidadeDTO toDTO(Cidade cidade) {
    	CidadeDTO cidadeDTO = new CidadeDTO();
    	cidadeDTO.setId(cidade.getId());
    	cidadeDTO.setNome(cidade.getNome());
    	cidadeDTO.setCapital(cidade.getCapital());
    	cidadeDTO.setUf(cidade.getUf());
    	return cidadeDTO;
    }
}