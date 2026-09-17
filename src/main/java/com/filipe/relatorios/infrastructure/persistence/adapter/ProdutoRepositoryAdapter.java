package com.filipe.relatorios.infrastructure.persistence.adapter;

import com.filipe.relatorios.domain.model.Produto;
import com.filipe.relatorios.domain.repository.ProdutoRepository;
import com.filipe.relatorios.infrastructure.persistence.entity.ProdutoEntity;
import com.filipe.relatorios.infrastructure.persistence.repository.ProdutoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepository
{
    private final ProdutoJpaRepository repository;

    public ProdutoRepositoryAdapter( ProdutoJpaRepository repository ) { this.repository = repository; }

    @Override
    public Produto salvar( Produto produto )
    {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setCdProduto( produto.getCdProduto() );
        entity.setNmProduto( produto.getNmProduto() );
        entity.setVlPreco( produto.getVlPreco() );
        ProdutoEntity saved = repository.save( entity );
        return new Produto( saved.getCdProduto(), saved.getNmProduto(), saved.getVlPreco() );
    }

    @Override
    public List<Produto> listar()
    {
        return repository.findAll().stream()
                .map( e -> new Produto( e.getCdProduto(), e.getNmProduto(), e.getVlPreco() ) )
                .toList();
    }

    @Override
    public Optional<Produto> buscarPorId( Long cdProduto )
    {
        return repository.findById( cdProduto )
                .map( e -> new Produto( e.getCdProduto(), e.getNmProduto(), e.getVlPreco() ) );
    }

    @Override
    public void excluir( Long cdProduto ) { repository.deleteById( cdProduto ); }
}
