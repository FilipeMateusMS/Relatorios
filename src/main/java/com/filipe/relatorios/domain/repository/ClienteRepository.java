package com.filipe.relatorios.domain.repository;

import com.filipe.relatorios.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository
{
    Cliente salvar( Cliente cliente );
    List<Cliente> listar();
    Optional<Cliente> buscarPorId( Long cdCliente );
    void excluir( Long cdCliente );
}
