package com.filipe.relatorios.application.dto.venda;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VendaRequest(
        @NotNull Long cdCliente,
        @NotNull Long cdProduto,
        @NotNull @Positive Integer qtQuantidade
)
{
}
