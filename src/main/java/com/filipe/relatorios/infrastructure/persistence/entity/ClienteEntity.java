package com.filipe.relatorios.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "cliente" )
public class ClienteEntity
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "cd_cliente" )
    private Long cdCliente;

    @Column( name = "nm_cliente", nullable = false, length = 100 )
    private String nmCliente;

    @Column( nullable = false, unique = true, length = 150 )
    private String email;

    public Long getCdCliente() { return cdCliente; }
    public void setCdCliente( Long cdCliente ) { this.cdCliente = cdCliente; }
    public String getNmCliente() { return nmCliente; }
    public void setNmCliente( String nmCliente ) { this.nmCliente = nmCliente; }
    public String getEmail() { return email; }
    public void setEmail( String email ) { this.email = email; }
}
