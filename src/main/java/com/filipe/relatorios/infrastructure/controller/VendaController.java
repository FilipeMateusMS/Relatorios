package com.filipe.relatorios.infrastructure.controller;

import com.filipe.relatorios.application.dto.venda.VendaRequest;
import com.filipe.relatorios.application.dto.venda.VendaResponse;
import com.filipe.relatorios.application.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController
{
    private final VendaService service;

    public VendaController( VendaService service ) { this.service = service; }

    @PostMapping
    public ResponseEntity<VendaResponse> criar( @Valid @RequestBody VendaRequest request, UriComponentsBuilder builder )
    {
        VendaResponse response = service.criar( request );
        URI uri = builder.path("/vendas/{id}").buildAndExpand( response.cdVenda() ).toUri();
        return ResponseEntity.created( uri ).body( response );
    }

    @GetMapping
    public ResponseEntity<List<VendaResponse>> listar() { return ResponseEntity.ok( service.listar() ); }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponse> buscarPorId( @PathVariable Long id ) { return ResponseEntity.ok( service.buscarPorId( id ) ); }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponse> atualizar( @PathVariable Long id, @Valid @RequestBody VendaRequest request ) { return ResponseEntity.ok( service.atualizar( id, request ) ); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir( @PathVariable Long id ) { service.excluir( id ); return ResponseEntity.noContent().build(); }
}
