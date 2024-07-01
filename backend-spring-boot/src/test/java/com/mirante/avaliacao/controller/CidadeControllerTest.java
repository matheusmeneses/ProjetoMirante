package com.mirante.avaliacao.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.exception.ResourceNotFoundException;
import com.mirante.avaliacao.service.ProjetoService;

@WebMvcTest(CidadeController.class)
public class CidadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPesquisarCidades() throws Exception {
        CidadeDTO cidade1 = new CidadeDTO(1L, "Florianópolis", "SC", true);
        CidadeDTO cidade2 = new CidadeDTO(2L, "Blumenau", "SC", false);
        when(service.pesquisarCidades()).thenReturn(Arrays.asList(cidade1, cidade2));

        mockMvc.perform(get("/cidades"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("Florianópolis"))
                .andExpect(jsonPath("$[1].nome").value("Blumenau"));
    }

    @Test
    void testIncluirCidade() throws Exception {
        CidadeDTO novaCidade = new CidadeDTO(null, "Joinville", "SC", false);
        Long cidadeId = 3L;
        when(service.incluirCidade(novaCidade)).thenReturn(cidadeId);

        mockMvc.perform(post("/cidades")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaCidade)))
                .andExpect(status().isOk())
                .andExpect(content().string(cidadeId.toString()));
    }

    @Test
    void testAlterarCidade() throws Exception {
        CidadeDTO cidadeExistente = new CidadeDTO(1L, "Blumenau", "SC", false);

        mockMvc.perform(put("/cidades")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cidadeExistente)))
                .andExpect(status().isOk());
    }

    @Test
    void testAlterarCidadeNotFound() throws Exception {
        CidadeDTO cidadeExistente = new CidadeDTO(1L, "Blumenau", "SC", false);
        doThrow(new ResourceNotFoundException("Cidade não encontrada")).when(service).alterarCidade(cidadeExistente);

        mockMvc.perform(put("/cidades")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cidadeExistente)))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Cidade não encontrada"));
    }

    @Test
    void testExcluirCidade() throws Exception {
        Long cidadeId = 1L;

        mockMvc.perform(delete("/cidades/{idCidade}", cidadeId))
                .andExpect(status().isOk());
    }
}
