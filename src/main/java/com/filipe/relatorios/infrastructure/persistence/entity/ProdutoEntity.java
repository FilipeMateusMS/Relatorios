package com.filipe.relatorios.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table( name = "produto" )
public class ProdutoEntity
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "cd_produto" )
    private Long cdProduto;

    @Column( name = "nm_produto", nullable = false, length = 150 )
    private String nmProduto;

    @Column( name = "vl_preco", nullable = false, precision = 12, scale = 2 )
    private BigDecimal vlPreco;

    public Long getCdProduto() { return cdProduto; }
    public void setCdProduto( Long cdProduto ) { this.cdProduto = cdProduto; }
    public String getNmProduto() { return nmProduto; }
    public void setNmProduto( String nmProduto ) { this.nmProduto = nmProduto; }
    public BigDecimal getVlPreco() { return vlPreco; }
    public void setVlPreco( BigDecimal vlPreco ) { this.vlPreco = vlPreco; }
}
