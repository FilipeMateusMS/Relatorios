package com.filipe.relatorios.infrastructure.controller;

import com.filipe.relatorios.application.dto.produto.ProdutoRequest;
import com.filipe.relatorios.application.dto.produto.ProdutoResponse;
import com.filipe.relatorios.application.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController
{
    private final ProdutoService service;

    public ProdutoController( ProdutoService service ) { this.service = service; }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar( @Valid @RequestBody ProdutoRequest request, UriComponentsBuilder builder )
    {
        ProdutoResponse response = service.criar( request );
        URI uri = builder.path("/produtos/{id}").buildAndExpand( response.cdProduto() ).toUri();
        return ResponseEntity.created( uri ).body( response );
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar() { return ResponseEntity.ok( service.listar() ); }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId( @PathVariable Long id ) { return ResponseEntity.ok( service.buscarPorId( id ) ); }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar( @PathVariable Long id, @Valid @RequestBody ProdutoRequest request ) { return ResponseEntity.ok( service.atualizar( id, request ) ); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir( @PathVariable Long id ) { service.excluir( id ); return ResponseEntity.noContent().build(); }
}
