package com.mirante.avaliacao.model.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.model.Cidade;

public class CidadeMapperTest {

    @Test
    void testMapperCidadeDTOToCidade() {
        CidadeDTO cidadeDTO = new CidadeDTO(1L, "Florianópolis", "SC", true);

        Cidade cidade = CidadeMapper.mapper(cidadeDTO);

        assertNotNull(cidade);
        assertEquals(cidadeDTO.getId(), cidade.getId());
        assertEquals(cidadeDTO.getNome(), cidade.getNome());
        assertEquals(cidadeDTO.getUf(), cidade.getUf());
        assertEquals(cidadeDTO.getCapital(), cidade.getCapital());
    }

    @Test
    void testMapperCidadeDTOToCidade_NullInput() {
        Cidade cidade = CidadeMapper.mapper(null);

        assertNull(cidade);
    }

    @Test
    void testMapperCidadeListToCidadeDTOList() {
        Cidade cidade1 = new Cidade(1L, "Florianópolis", "SC", true);
        Cidade cidade2 = new Cidade(2L, "Blumenau", "SC", false);
        List<Cidade> cidades = Arrays.asList(cidade1, cidade2);

        List<CidadeDTO> cidadeDTOs = CidadeMapper.mapperList(cidades);

        assertNotNull(cidadeDTOs);
        assertEquals(2, cidadeDTOs.size());
        assertEquals(cidade1.getId(), cidadeDTOs.get(0).getId());
        assertEquals(cidade1.getNome(), cidadeDTOs.get(0).getNome());
        assertEquals(cidade1.getUf(), cidadeDTOs.get(0).getUf());
        assertEquals(cidade1.getCapital(), cidadeDTOs.get(0).getCapital());
        assertEquals(cidade2.getId(), cidadeDTOs.get(1).getId());
        assertEquals(cidade2.getNome(), cidadeDTOs.get(1).getNome());
        assertEquals(cidade2.getUf(), cidadeDTOs.get(1).getUf());
        assertEquals(cidade2.getCapital(), cidadeDTOs.get(1).getCapital());
    }

    @Test
    void testMapperCidadeListToCidadeDTOList_EmptyList() {
        List<Cidade> cidades = Arrays.asList();

        List<CidadeDTO> cidadeDTOs = CidadeMapper.mapperList(cidades);

        assertNotNull(cidadeDTOs);
        assertTrue(cidadeDTOs.isEmpty());
    }
}
