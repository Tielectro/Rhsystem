package com.rhsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "formulario")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomecompleto")
    private String nomeCompleto;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "numerodocumento")
    private String numeroDocumento;

    // Novos campos adicionados
    @Column(name = "cursos")
    private String cursos;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "puesto")
    private String puesto;

    @Column(name = "tiempopermanencia")
    private String tiempoPermanencia;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "disponibilidad")
    private String disponibilidad;

    @Column(name = "diasconfirmar")
    private String diasConfirmar;

    @Column(name = "salariopretendido")
    private String salarioPretendido;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTiempoPermanencia() {
        return tiempoPermanencia;
    }

    public void setTiempoPermanencia(String tiempoPermanencia) {
        this.tiempoPermanencia = tiempoPermanencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getDiasConfirmar() {
        return diasConfirmar;
    }

    public void setDiasConfirmar(String diasConfirmar) {
        this.diasConfirmar = diasConfirmar;
    }

    public String getSalarioPretendido() {
        return salarioPretendido;
    }

    public void setSalarioPretendido(String salarioPretendido) {
        this.salarioPretendido = salarioPretendido;
    }

    // Método toString para facilitar a depuração
    @Override
    public String toString() {
        return "Formulario{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", descricao='" + descricao + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", cursos='" + cursos + '\'' +
                ", empresa='" + empresa + '\'' +
                ", puesto='" + puesto + '\'' +
                ", tiempoPermanencia='" + tiempoPermanencia + '\'' +
                ", referencia='" + referencia + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", diasConfirmar='" + diasConfirmar + '\'' +
                ", salarioPretendido='" + salarioPretendido + '\'' +
                '}';
    }
}
