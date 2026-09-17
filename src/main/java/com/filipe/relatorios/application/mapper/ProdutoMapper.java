package com.filipe.relatorios.application.mapper;

import com.filipe.relatorios.application.dto.produto.ProdutoResponse;
import com.filipe.relatorios.domain.model.Produto;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface ProdutoMapper
{
    ProdutoResponse toResponse( Produto produto );
}
