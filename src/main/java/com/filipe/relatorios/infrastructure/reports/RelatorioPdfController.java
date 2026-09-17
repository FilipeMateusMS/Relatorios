package com.filipe.relatorios.infrastructure.reports;

import com.filipe.relatorios.domain.model.Venda;
import com.filipe.relatorios.domain.repository.VendaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping( "/relatorios/vendas" )
public class RelatorioPdfController
{
    private final VendaRepository vendaRepository;
    private final RelatorioPdfService relatorioPdfService;

    public RelatorioPdfController( VendaRepository vendaRepository, RelatorioPdfService relatorioPdfService )
    {
        this.vendaRepository = vendaRepository;
        this.relatorioPdfService = relatorioPdfService;
    }

    @GetMapping( "/pdf" )
    public ResponseEntity<byte[]> gerarPdf(
            @RequestParam @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime inicio,
            @RequestParam @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime fim )
    {
        List<Venda> vendas = vendaRepository.buscarPorPeriodo( inicio, fim );
        byte[] arquivo = relatorioPdfService.gerar( vendas );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_PDF );
        headers.setContentDisposition( ContentDisposition.attachment().filename( "relatorio-vendas.pdf" ).build() );

        return ResponseEntity.ok().headers( headers ).body( arquivo );
    }
}
