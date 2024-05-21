package com.viajefeliza.alquiler.model;
import jakarta.persistence.*;



@Entity
@Table(name = "temporada", uniqueConstraints = @UniqueConstraint(columnNames = "temporada"))
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_temporada", nullable = false)
    private Integer idTemporada;

    @Column(name = "temporada", nullable = false)
    private String temporada;

    @Column (name = "porcentaje_aumento", nullable = false)
    private Float porcentajeAumento;

    public Integer getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(Integer idTemporada) {
        this.idTemporada = idTemporada;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public Float getPorcentajeAumento() {
        return porcentajeAumento;
    }

    public void setPorcentajeAumento(Float porcentajeAumento) {
        this.porcentajeAumento = porcentajeAumento;
    }
}

