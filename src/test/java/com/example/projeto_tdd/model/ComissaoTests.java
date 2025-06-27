package com.example.projeto_tdd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class ComissaoTests {

    @TestConfiguration
    static class ComissaoConfiguracao {

        @Bean
        public Comissao comissao(){
            return new Comissao();
        }  
    }

    @Autowired
    Comissao comissao ;

    @Test
    public void deve_calcular_100_reais_de_comissao_para_venda_de_1000_com_10_por_cento(){
        // Arrange = preparação
        
        Double valorVenda = 1000.00;
        Double valorComissao = 100.0;

        //Act = Ação
        Double valorCalculado = comissao.calcular(valorVenda);
        System.out.println("Valor calculado = " + valorCalculado);

        //Assertt = Confirmação
       Assertions.assertEquals(valorComissao, valorCalculado);
       System.out.println("Valor da comissao = " + valorComissao);

    }

      @Test
    public void deve_calcular_300_reais_de_comissao_para_venda_de_1000_com_15_por_cento(){
        // Arrange = preparação
        
        Double valorVenda = 2000.00;
        Double valorComissao = 300.0;

        //Act = Ação
        Double valorCalculado = comissao.calcular(valorVenda);
        System.out.println("Valor calculado = " + valorCalculado);

        //Assertt = Confirmação
       Assertions.assertEquals(valorComissao, valorCalculado);
       System.out.println("Valor da comissao = " + valorComissao);

    }


}
