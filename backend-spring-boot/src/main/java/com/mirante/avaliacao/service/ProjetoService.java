package com.mirante.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.exception.BussinesException;
import com.mirante.avaliacao.exception.ResourceNotFoundException;
import com.mirante.avaliacao.model.Cidade;
import com.mirante.avaliacao.model.mapper.CidadeMapper;
import com.mirante.avaliacao.repository.CidadeRepository;

import lombok.AllArgsConstructor;
import lombok.Generated;

//------------------------------------------------------------------
/** Service usado para acessar os repositórios da aplicação */
//------------------------------------------------------------------
@Generated
@AllArgsConstructor
@Service
public class ProjetoService {

	private final CidadeRepository repository ;
	
	//---------------------------------------------------------
	/** Método que retorna todas as cidades cadastradas */
	//---------------------------------------------------------
	public List<CidadeDTO> pesquisarCidades() {
		return CidadeMapper.mapperList(repository.findAll()) ;
	}

	//----------------------------------------------------------
	/** Método chamado para incluir uma nova cidade */
	//----------------------------------------------------------	
	public Long incluirCidade(CidadeDTO dto) {
		if(dto.getUf() != null && dto.getUf().length() != 2) {
			throw new BussinesException("UF deve ter apenas 2 caractes.");
		}
		Cidade cidade = repository.save(CidadeMapper.mapper(dto));
		return cidade.getId();
	}

	//----------------------------------------------------------
	/** Método chamado para alterar os dados de uma cidade */
	//----------------------------------------------------------	
	public Cidade alterarCidade(CidadeDTO dto) {
		Optional<Cidade> cidadeOpcional = repository.findById(dto.getId());
		if(cidadeOpcional.isPresent()) {
			Cidade cidade = cidadeOpcional.get();
			cidade.setNome(dto.getNome());
			cidade.setCapital(dto.getCapital());
			cidade.setUf(dto.getUf());
			return repository.save(cidade); 
		}else {
			throw new ResourceNotFoundException("Cidade " + dto.getId() + " não cadastrado");
		}
	}

	//----------------------------------------------------------
	/** Método chamado para excluir uma cidade */
	//----------------------------------------------------------	
	public void excluirCidade(Long idCidade) {
		repository.deleteById(idCidade);
	}
}
