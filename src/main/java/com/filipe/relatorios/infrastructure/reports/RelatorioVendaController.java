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
public class RelatorioVendaController
{
    private final VendaRepository vendaRepository;
    private final RelatorioVendaService relatorioVendaService;

    public RelatorioVendaController( VendaRepository vendaRepository, RelatorioVendaService relatorioVendaService )
    {
        this.vendaRepository = vendaRepository;
        this.relatorioVendaService = relatorioVendaService;
    }

    @GetMapping( "/excel" )
    public ResponseEntity<byte[]> gerarExcel(
            @RequestParam @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime inicio,
            @RequestParam @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime fim )
    {
        List<Venda> vendas = vendaRepository.buscarPorPeriodo( inicio, fim );
        byte[] arquivo = relatorioVendaService.gerarExcel( vendas );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_OCTET_STREAM );
        headers.setContentDisposition( ContentDisposition.attachment().filename( "relatorio-vendas.xlsx" ).build() );

        return ResponseEntity.ok().headers( headers ).body( arquivo );
    }

    @GetMapping( "/resumo" )
    public RelatorioVendaService.ResumoVenda resumo(
            @RequestParam @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime inicio,
            @RequestParam @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime fim )
    {
        return relatorioVendaService.resumir( vendaRepository.buscarPorPeriodo( inicio, fim ) );
    }
}
