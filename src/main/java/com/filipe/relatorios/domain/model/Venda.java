package com.filipe.relatorios.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venda
{
    private Long cdVenda;
    private Cliente cliente;
    private Produto produto;
    private Integer qtQuantidade;
    private BigDecimal vlTotal;
    private LocalDateTime dtVenda;

    public Venda() { }

    public Venda( Long cdVenda, Cliente cliente, Produto produto, Integer qtQuantidade, BigDecimal vlTotal, LocalDateTime dtVenda )
    {
        this.cdVenda = cdVenda;
        this.cliente = cliente;
        this.produto = produto;
        this.qtQuantidade = qtQuantidade;
        this.vlTotal = vlTotal;
        this.dtVenda = dtVenda;
    }

    public Long getCdVenda() { return cdVenda; }
    public void setCdVenda( Long cdVenda ) { this.cdVenda = cdVenda; }
    public Cliente getCliente() { return cliente; }
    public void setCliente( Cliente cliente ) { this.cliente = cliente; }
    public Produto getProduto() { return produto; }
    public void setProduto( Produto produto ) { this.produto = produto; }
    public Integer getQtQuantidade() { return qtQuantidade; }
    public void setQtQuantidade( Integer qtQuantidade ) { this.qtQuantidade = qtQuantidade; }
    public BigDecimal getVlTotal() { return vlTotal; }
    public void setVlTotal( BigDecimal vlTotal ) { this.vlTotal = vlTotal; }
    public LocalDateTime getDtVenda() { return dtVenda; }
    public void setDtVenda( LocalDateTime dtVenda ) { this.dtVenda = dtVenda; }
}
