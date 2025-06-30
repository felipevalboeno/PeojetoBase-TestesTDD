package com.example.projeto_tdd.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.projeto_tdd.model.Produto;
import com.example.projeto_tdd.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class ProdutoControllerTest {

    // MockMVC
    // Mockito
    // Mock

    @Autowired
    private ProdutoController produtoController;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // aqui será executado antes de qualquer teste

        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    public void deve_retornar_status_200_ok_ao_obter_todos_os_produtos() throws Exception {
        // Arrange = preparação
        // List<Produto> produtos = new ArrayList<Produto>();
        var requestBuilder = MockMvcRequestBuilders.get("/api/produtos");

        this.mockMvc.perform(requestBuilder) // Act = ação
                .andExpect(MockMvcResultMatchers.status().isBadRequest()); // Assert = confirmação

    }

    @Test
    public void deve_retornar_o_produto_por_id() throws Exception {
        // Arrange = preparação

        Produto produto = new Produto();
        produto.setId(2l);
        produto.setNome("martelo");
        produto.setQuantidade(10);

        Optional<Produto> optProduto = Optional.of(produto);

        // List<Produto> produtos = new ArrayList<Produto>();
        var requestBuilder = MockMvcRequestBuilders.get("/api/produtos/2");
        when(this.produtoService.obterPorId(2l)).thenReturn(optProduto);
        ;
        this.mockMvc.perform(requestBuilder) // Act = ação
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2l)); // Assert = confirmação

    }

    @Test
    public void deve_adicionar_o_produto() throws Exception {
        // Arrange = preparação

        Produto produto = new Produto();
        produto.setNome("martelo");
        produto.setQuantidade(10);

        // convertendo o produto em json
        String json = new ObjectMapper().writeValueAsString(produto);

        // List<Produto> produtos = new ArrayList<Produto>();
        var requestBuilder = MockMvcRequestBuilders.post("/api/produtos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        // adicionando o id ao produto que vamos retornar
        produto.setId(1l);

        when(this.produtoService.adicionar(produto)).thenReturn(produto);
        ;
        this.mockMvc.perform(requestBuilder) // Act = ação
                .andExpect(MockMvcResultMatchers.status().isCreated()); // Assert = confirmação

    }

    @Test
    public void deve_deletar_produto_por_id() throws Exception {
        Long id = 1L;

        // Arrange
        doNothing().when(produtoService).deletar(id);

        // Act
        var requestBuilder = MockMvcRequestBuilders.delete("/api/produtos/1");

        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Assert
        verify(produtoService, times(1)).deletar(id);
    }

   @Test
public void deve_atualizar_produto_por_id() throws Exception {
    Long id = 1L;

    Produto produto = new Produto();
    produto.setNome("martelo");
    produto.setQuantidade(10);

    Produto produtoAtualizado = new Produto();
    produtoAtualizado.setId(id);
    produtoAtualizado.setNome("martelo");
    produtoAtualizado.setQuantidade(10);

    String json = new ObjectMapper().writeValueAsString(produto);

    when(produtoService.atualizar(eq(id), any(Produto.class)))
            .thenReturn(produtoAtualizado);

    this.mockMvc.perform(MockMvcRequestBuilders
            .put("/api/produtos/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("martelo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantidade").value(10));

    verify(produtoService, times(1)).atualizar(eq(id), any(Produto.class));
}



}
