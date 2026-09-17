package com.filipe.relatorios.domain.repository;

import com.filipe.relatorios.domain.model.Venda;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VendaRepository
{
    Venda salvar( Venda venda );
    List<Venda> listar();
    Optional<Venda> buscarPorId( Long cdVenda );
    void excluir( Long cdVenda );
    List<Venda> buscarPorPeriodo( LocalDateTime inicio, LocalDateTime fim );
}
