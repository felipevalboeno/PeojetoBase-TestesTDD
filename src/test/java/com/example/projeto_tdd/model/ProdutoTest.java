package com.example.projeto_tdd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProdutoTest {

    @Test
    public void deve_calcular_valor_total_produto(){

        Produto produto = new Produto();
        produto.setQuantidade(10);
        produto.setDesconto(10.0);
        produto.setAcrescimo(0.0);
        produto.setValor(10.0);

        double resultadoEsperado = 90.0;

        Double resultado = produto.calcularValorTotal();

        Assertions.assertEquals(resultadoEsperado, resultado);


    }
    
}
