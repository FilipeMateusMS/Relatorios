package com.filipe.relatorios.application.mapper;

import com.filipe.relatorios.application.dto.cliente.ClienteResponse;
import com.filipe.relatorios.domain.model.Cliente;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface ClienteMapper
{
    ClienteResponse toResponse( Cliente cliente );
}
