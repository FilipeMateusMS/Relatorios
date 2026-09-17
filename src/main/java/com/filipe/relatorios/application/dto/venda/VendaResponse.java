package com.filipe.relatorios.application.dto.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaResponse(
        Long cdVenda,
        Long cdCliente,
        Long cdProduto,
        Integer qtQuantidade,
        BigDecimal vlTotal,
        LocalDateTime dtVenda
)
{
}
