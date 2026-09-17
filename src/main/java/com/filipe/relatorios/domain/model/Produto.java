package com.filipe.relatorios.domain.model;

import java.math.BigDecimal;

public class Produto
{
    private Long cdProduto;
    private String nmProduto;
    private BigDecimal vlPreco;

    public Produto() { }

    public Produto( Long cdProduto, String nmProduto, BigDecimal vlPreco )
    {
        this.cdProduto = cdProduto;
        this.nmProduto = nmProduto;
        this.vlPreco = vlPreco;
    }

    public Long getCdProduto() { return cdProduto; }
    public void setCdProduto( Long cdProduto ) { this.cdProduto = cdProduto; }
    public String getNmProduto() { return nmProduto; }
    public void setNmProduto( String nmProduto ) { this.nmProduto = nmProduto; }
    public BigDecimal getVlPreco() { return vlPreco; }
    public void setVlPreco( BigDecimal vlPreco ) { this.vlPreco = vlPreco; }
}
