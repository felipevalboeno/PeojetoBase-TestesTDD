package com.example.projeto_tdd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.projeto_tdd.model.Produto;

@Service
public class ProdutoService {

    public List<Produto> obterTodos() {
        return new ArrayList<>();
    }

    public Optional<Produto> obterPorId(Long id) {
        return Optional.of(new Produto());
    }

    public Produto adicionar(Produto produto) {
        return produto;
    }
}