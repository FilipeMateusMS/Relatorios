package com.filipe.relatorios.infrastructure.controller;

import com.filipe.relatorios.application.dto.cliente.ClienteRequest;
import com.filipe.relatorios.application.dto.cliente.ClienteResponse;
import com.filipe.relatorios.application.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController
{
    private final ClienteService service;

    public ClienteController( ClienteService service ) { this.service = service; }

    @PostMapping
    public ResponseEntity<ClienteResponse> criar( @Valid @RequestBody ClienteRequest request, UriComponentsBuilder builder )
    {
        ClienteResponse response = service.criar( request );
        URI uri = builder.path("/clientes/{id}").buildAndExpand( response.cdCliente() ).toUri();
        return ResponseEntity.created( uri ).body( response );
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() { return ResponseEntity.ok( service.listar() ); }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId( @PathVariable Long id ) { return ResponseEntity.ok( service.buscarPorId( id ) ); }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar( @PathVariable Long id, @Valid @RequestBody ClienteRequest request ) { return ResponseEntity.ok( service.atualizar( id, request ) ); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir( @PathVariable Long id ) { service.excluir( id ); return ResponseEntity.noContent().build(); }
}
