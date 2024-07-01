package com.mirante.avaliacao.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.model.Cidade;

public class CidadeMapper {

    public static Cidade mapper(CidadeDTO cidadeDTO) {
        if (cidadeDTO == null) {
            return null;
        }
        Cidade cidade = new Cidade();
        cidade.setId(cidadeDTO.getId());
        cidade.setNome(cidadeDTO.getNome());
        cidade.setCapital(cidadeDTO.getCapital());
        cidade.setUf(cidadeDTO.getUf());
        return cidade;
    }

    public static List<CidadeDTO> mapperList(List<Cidade> cidades) {
        List<CidadeDTO> cidadeDTOs = new ArrayList<>();
        for (Cidade cidade : cidades) {
            CidadeDTO cidadeDTO = CidadeDTO.toDTO(cidade);
            cidadeDTOs.add(cidadeDTO);
        }
        return cidadeDTOs;
    }
}
