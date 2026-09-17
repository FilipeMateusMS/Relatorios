package com.filipe.relatorios.domain.model;

public class Cliente
{
    private Long cdCliente;
    private String nmCliente;
    private String email;

    public Cliente() { }

    public Cliente( Long cdCliente, String nmCliente, String email )
    {
        this.cdCliente = cdCliente;
        this.nmCliente = nmCliente;
        this.email = email;
    }

    public Long getCdCliente() { return cdCliente; }
    public void setCdCliente( Long cdCliente ) { this.cdCliente = cdCliente; }
    public String getNmCliente() { return nmCliente; }
    public void setNmCliente( String nmCliente ) { this.nmCliente = nmCliente; }
    public String getEmail() { return email; }
    public void setEmail( String email ) { this.email = email; }
}
