package com.filipe.relatorios.application.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nmCliente,
        @NotBlank @Email String email
)
{
}
