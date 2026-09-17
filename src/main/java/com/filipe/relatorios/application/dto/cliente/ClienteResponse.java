package com.filipe.relatorios.application.dto.cliente;

public record ClienteResponse(
        Long cdCliente,
        String nmCliente,
        String email
)
{
}
