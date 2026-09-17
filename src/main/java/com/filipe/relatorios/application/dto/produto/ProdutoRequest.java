package com.filipe.relatorios.application.dto.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank String nmProduto,
        @NotNull @DecimalMin("0.00") BigDecimal vlPreco
)
{
}
