package com.filipe.relatorios.application.service;

import com.filipe.relatorios.application.dto.venda.VendaRequest;
import com.filipe.relatorios.application.dto.venda.VendaResponse;
import com.filipe.relatorios.application.mapper.VendaMapper;
import com.filipe.relatorios.domain.exception.RecursoNaoEncontradoException;
import com.filipe.relatorios.domain.model.Cliente;
import com.filipe.relatorios.domain.model.Produto;
import com.filipe.relatorios.domain.model.Venda;
import com.filipe.relatorios.domain.repository.ClienteRepository;
import com.filipe.relatorios.domain.repository.ProdutoRepository;
import com.filipe.relatorios.domain.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService
{
    private final VendaRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaMapper mapper;

    public VendaService( VendaRepository repository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, VendaMapper mapper )
    {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public VendaResponse criar( VendaRequest request )
    {
        Cliente cliente = buscarCliente( request.cdCliente() );
        Produto produto = buscarProduto( request.cdProduto() );
        BigDecimal total = produto.getVlPreco().multiply( BigDecimal.valueOf( request.qtQuantidade() ) );
        Venda venda = new Venda( null, cliente, produto, request.qtQuantidade(), total, LocalDateTime.now() );
        return mapper.toResponse( repository.salvar( venda ) );
    }

    @Transactional(readOnly = true)
    public List<VendaResponse> listar() { return repository.listar().stream().map( mapper::toResponse ).toList(); }

    @Transactional(readOnly = true)
    public VendaResponse buscarPorId( Long id ) { return mapper.toResponse( buscar( id ) ); }

    @Transactional
    public VendaResponse atualizar( Long id, VendaRequest request )
    {
        Venda venda = buscar( id );
        Cliente cliente = buscarCliente( request.cdCliente() );
        Produto produto = buscarProduto( request.cdProduto() );
        venda.setCliente( cliente );
        venda.setProduto( produto );
        venda.setQtQuantidade( request.qtQuantidade() );
        venda.setVlTotal( produto.getVlPreco().multiply( BigDecimal.valueOf( request.qtQuantidade() ) ) );
        return mapper.toResponse( repository.salvar( venda ) );
    }

    @Transactional
    public void excluir( Long id ) { buscar( id ); repository.excluir( id ); }

    private Venda buscar( Long id )
    {
        return repository.buscarPorId( id ).orElseThrow( () -> new RecursoNaoEncontradoException( "Venda não encontrada: " + id ) );
    }

    private Cliente buscarCliente( Long id )
    {
        return clienteRepository.buscarPorId( id ).orElseThrow( () -> new RecursoNaoEncontradoException( "Cliente não encontrado: " + id ) );
    }

    private Produto buscarProduto( Long id )
    {
        return produtoRepository.buscarPorId( id ).orElseThrow( () -> new RecursoNaoEncontradoException( "Produto não encontrado: " + id ) );
    }
}
