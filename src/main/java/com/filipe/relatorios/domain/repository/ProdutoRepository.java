package com.filipe.relatorios.domain.repository;

import com.filipe.relatorios.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository
{
    Produto salvar( Produto produto );
    List<Produto> listar();
    Optional<Produto> buscarPorId( Long cdProduto );
    void excluir( Long cdProduto );
}
