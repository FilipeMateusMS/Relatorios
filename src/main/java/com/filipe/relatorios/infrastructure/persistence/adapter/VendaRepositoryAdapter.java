package com.filipe.relatorios.infrastructure.persistence.adapter;

import com.filipe.relatorios.domain.model.Cliente;
import com.filipe.relatorios.domain.model.Produto;
import com.filipe.relatorios.domain.model.Venda;
import com.filipe.relatorios.domain.repository.VendaRepository;
import com.filipe.relatorios.infrastructure.persistence.entity.VendaEntity;
import com.filipe.relatorios.infrastructure.persistence.repository.ClienteJpaRepository;
import com.filipe.relatorios.infrastructure.persistence.repository.ProdutoJpaRepository;
import com.filipe.relatorios.infrastructure.persistence.repository.VendaJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class VendaRepositoryAdapter implements VendaRepository
{
    private final VendaJpaRepository repository;
    private final ClienteJpaRepository clienteRepository;
    private final ProdutoJpaRepository produtoRepository;

    public VendaRepositoryAdapter( VendaJpaRepository repository, ClienteJpaRepository clienteRepository, ProdutoJpaRepository produtoRepository )
    {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Venda salvar( Venda venda )
    {
        VendaEntity entity = new VendaEntity();
        entity.setCdVenda( venda.getCdVenda() );
        entity.setCliente( clienteRepository.getReferenceById( venda.getCliente().getCdCliente() ) );
        entity.setProduto( produtoRepository.getReferenceById( venda.getProduto().getCdProduto() ) );
        entity.setQtQuantidade( venda.getQtQuantidade() );
        entity.setVlTotal( venda.getVlTotal() );
        entity.setDtVenda( venda.getDtVenda() );
        return toDomain( repository.save( entity ) );
    }

    @Override
    public List<Venda> listar() { return repository.findAll().stream().map( this::toDomain ).toList(); }

    @Override
    public Optional<Venda> buscarPorId( Long cdVenda ) { return repository.findById( cdVenda ).map( this::toDomain ); }

    @Override
    public void excluir( Long cdVenda ) { repository.deleteById( cdVenda ); }

    @Override
    public List<Venda> buscarPorPeriodo( LocalDateTime inicio, LocalDateTime fim )
    {
        return repository.findByDtVendaBetween( inicio, fim ).stream().map( this::toDomain ).toList();
    }

    private Venda toDomain( VendaEntity entity )
    {
        Cliente cliente = new Cliente( entity.getCliente().getCdCliente(), entity.getCliente().getNmCliente(), entity.getCliente().getEmail() );
        Produto produto = new Produto( entity.getProduto().getCdProduto(), entity.getProduto().getNmProduto(), entity.getProduto().getVlPreco() );
        return new Venda( entity.getCdVenda(), cliente, produto, entity.getQtQuantidade(), entity.getVlTotal(), entity.getDtVenda() );
    }
}
