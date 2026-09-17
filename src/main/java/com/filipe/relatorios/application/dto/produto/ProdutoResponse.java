package com.filipe.relatorios.application.dto.produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long cdProduto,
        String nmProduto,
        BigDecimal vlPreco
)
{
}
