package com.example.projeto_tdd.model;

public class Comissao {
    

    public Double calcular(Double valorVenda){
        //parâmetro ternário
             
        return (valorVenda > 1000.00) ? valorVenda * 0.15 : valorVenda * 0.10;
    
    }
}
