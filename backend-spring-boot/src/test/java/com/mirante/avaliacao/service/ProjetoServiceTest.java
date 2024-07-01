package com.mirante.avaliacao.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.exception.ResourceNotFoundException;
import com.mirante.avaliacao.model.Cidade;
import com.mirante.avaliacao.repository.CidadeRepository;

public class ProjetoServiceTest {

    @Mock
    private CidadeRepository repository;

    @InjectMocks
    private ProjetoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPesquisarCidades() {
        Cidade cidade1 = new Cidade(1L, "Florianópolis", "SC", true);
        Cidade cidade2 = new Cidade(2L, "Blumenau", "SC", false);
        when(repository.findAll()).thenReturn(Arrays.asList(cidade1, cidade2));

        List<CidadeDTO> result = service.pesquisarCidades();

        assertEquals(2, result.size());
        assertEquals("Florianópolis", result.get(0).getNome());
        assertEquals("Blumenau", result.get(1).getNome());
    }

    @Test
    void testIncluirCidade() {
        CidadeDTO cidadeDTO = new CidadeDTO(null, "Joinville", "SC", false);
        Cidade cidade = new Cidade(3L, "Joinville", "SC", false);
        when(repository.save(any(Cidade.class))).thenReturn(cidade);

        Long result = service.incluirCidade(cidadeDTO);

        assertEquals(3L, result);
    }

    @Test
    void testAlterarCidade() {
        CidadeDTO cidadeDTO = new CidadeDTO(1L, "Blumenau", "SC", false);
        Cidade cidade = new Cidade(1L, "Blumenau", "SC", false);
        when(repository.findById(1L)).thenReturn(Optional.of(cidade));
        when(repository.save(any(Cidade.class))).thenReturn(cidade);

        Cidade result = service.alterarCidade(cidadeDTO);

        assertEquals("Blumenau", result.getNome());
    }

    @Test
    void testAlterarCidadeNotFound() {
        CidadeDTO cidadeDTO = new CidadeDTO(1L, "Blumenau", "SC", false);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.alterarCidade(cidadeDTO);
        });

        assertEquals("Cidade 1 não cadastrado", exception.getMessage());
    }

    @Test
    void testExcluirCidade() {
        Long cidadeId = 1L;
        doNothing().when(repository).deleteById(cidadeId);

        service.excluirCidade(cidadeId);

        verify(repository, times(1)).deleteById(cidadeId);
    }
}
