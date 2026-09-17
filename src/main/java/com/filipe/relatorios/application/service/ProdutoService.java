package com.filipe.relatorios.application.service;

import com.filipe.relatorios.application.dto.produto.ProdutoRequest;
import com.filipe.relatorios.application.dto.produto.ProdutoResponse;
import com.filipe.relatorios.application.mapper.ProdutoMapper;
import com.filipe.relatorios.domain.exception.RecursoNaoEncontradoException;
import com.filipe.relatorios.domain.model.Produto;
import com.filipe.relatorios.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService
{
    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoService( ProdutoRepository repository, ProdutoMapper mapper )
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ProdutoResponse criar( ProdutoRequest request )
    {
        Produto produto = new Produto( null, request.nmProduto(), request.vlPreco() );
        return mapper.toResponse( repository.salvar( produto ) );
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listar() { return repository.listar().stream().map( mapper::toResponse ).toList(); }

    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId( Long id ) { return mapper.toResponse( buscar( id ) ); }

    @Transactional
    public ProdutoResponse atualizar( Long id, ProdutoRequest request )
    {
        Produto produto = buscar( id );
        produto.setNmProduto( request.nmProduto() );
        produto.setVlPreco( request.vlPreco() );
        return mapper.toResponse( repository.salvar( produto ) );
    }

    @Transactional
    public void excluir( Long id ) { buscar( id ); repository.excluir( id ); }

    private Produto buscar( Long id )
    {
        return repository.buscarPorId( id ).orElseThrow( () -> new RecursoNaoEncontradoException( "Produto não encontrado: " + id ) );
    }
}
