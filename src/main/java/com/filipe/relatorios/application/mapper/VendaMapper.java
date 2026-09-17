package com.filipe.relatorios.application.mapper;

import com.filipe.relatorios.application.dto.venda.VendaResponse;
import com.filipe.relatorios.domain.model.Venda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring" )
public interface VendaMapper
{
    @Mapping( target = "cdCliente", source = "cliente.cdCliente" )
    @Mapping( target = "cdProduto", source = "produto.cdProduto" )
    VendaResponse toResponse( Venda venda );
}
