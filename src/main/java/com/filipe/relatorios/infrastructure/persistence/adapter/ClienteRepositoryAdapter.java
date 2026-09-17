package com.filipe.relatorios.infrastructure.persistence.adapter;

import com.filipe.relatorios.domain.model.Cliente;
import com.filipe.relatorios.domain.repository.ClienteRepository;
import com.filipe.relatorios.infrastructure.persistence.entity.ClienteEntity;
import com.filipe.relatorios.infrastructure.persistence.repository.ClienteJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepository
{
    private final ClienteJpaRepository repository;

    public ClienteRepositoryAdapter( ClienteJpaRepository repository ) { this.repository = repository; }

    @Override
    public Cliente salvar( Cliente cliente )
    {
        ClienteEntity entity = new ClienteEntity();
        entity.setCdCliente( cliente.getCdCliente() );
        entity.setNmCliente( cliente.getNmCliente() );
        entity.setEmail( cliente.getEmail() );
        ClienteEntity saved = repository.save( entity );
        return new Cliente( saved.getCdCliente(), saved.getNmCliente(), saved.getEmail() );
    }

    @Override
    public List<Cliente> listar()
    {
        return repository.findAll().stream()
                .map( e -> new Cliente( e.getCdCliente(), e.getNmCliente(), e.getEmail() ) )
                .toList();
    }

    @Override
    public Optional<Cliente> buscarPorId( Long cdCliente )
    {
        return repository.findById( cdCliente )
                .map( e -> new Cliente( e.getCdCliente(), e.getNmCliente(), e.getEmail() ) );
    }

    @Override
    public void excluir( Long cdCliente ) { repository.deleteById( cdCliente ); }
}
