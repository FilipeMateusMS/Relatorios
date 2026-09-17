package com.filipe.relatorios.application.service;

import com.filipe.relatorios.application.dto.cliente.ClienteRequest;
import com.filipe.relatorios.application.dto.cliente.ClienteResponse;
import com.filipe.relatorios.application.mapper.ClienteMapper;
import com.filipe.relatorios.domain.exception.RecursoNaoEncontradoException;
import com.filipe.relatorios.domain.model.Cliente;
import com.filipe.relatorios.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService
{
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService( ClienteRepository repository, ClienteMapper mapper )
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ClienteResponse criar( ClienteRequest request )
    {
        Cliente cliente = new Cliente( null, request.nmCliente(), request.email() );
        return mapper.toResponse( repository.salvar( cliente ) );
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() { return repository.listar().stream().map( mapper::toResponse ).toList(); }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId( Long id ) { return mapper.toResponse( buscar( id ) ); }

    @Transactional
    public ClienteResponse atualizar( Long id, ClienteRequest request )
    {
        Cliente cliente = buscar( id );
        cliente.setNmCliente( request.nmCliente() );
        cliente.setEmail( request.email() );
        return mapper.toResponse( repository.salvar( cliente ) );
    }

    @Transactional
    public void excluir( Long id ) { buscar( id ); repository.excluir( id ); }

    private Cliente buscar( Long id )
    {
        return repository.buscarPorId( id ).orElseThrow( () -> new RecursoNaoEncontradoException( "Cliente não encontrado: " + id ) );
    }
}
