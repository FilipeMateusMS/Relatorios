package com.filipe.relatorios.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table( name = "venda" )
public class VendaEntity
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "cd_venda" )
    private Long cdVenda;

    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "cd_cliente", nullable = false )
    private ClienteEntity cliente;

    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "cd_produto", nullable = false )
    private ProdutoEntity produto;

    @Column( name = "qt_quantidade", nullable = false )
    private Integer qtQuantidade;

    @Column( name = "vl_total", nullable = false, precision = 12, scale = 2 )
    private BigDecimal vlTotal;

    @Column( name = "dt_venda", nullable = false )
    private LocalDateTime dtVenda;

    public Long getCdVenda() { return cdVenda; }
    public void setCdVenda( Long cdVenda ) { this.cdVenda = cdVenda; }
    public ClienteEntity getCliente() { return cliente; }
    public void setCliente( ClienteEntity cliente ) { this.cliente = cliente; }
    public ProdutoEntity getProduto() { return produto; }
    public void setProduto( ProdutoEntity produto ) { this.produto = produto; }
    public Integer getQtQuantidade() { return qtQuantidade; }
    public void setQtQuantidade( Integer qtQuantidade ) { this.qtQuantidade = qtQuantidade; }
    public BigDecimal getVlTotal() { return vlTotal; }
    public void setVlTotal( BigDecimal vlTotal ) { this.vlTotal = vlTotal; }
    public LocalDateTime getDtVenda() { return dtVenda; }
    public void setDtVenda( LocalDateTime dtVenda ) { this.dtVenda = dtVenda; }
}
