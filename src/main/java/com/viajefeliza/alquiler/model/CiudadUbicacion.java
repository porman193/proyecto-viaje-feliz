package com.viajefeliza.alquiler.model;

import jakarta.persistence.*;



@Entity
@Table(name = "ciudad_ubicacion", uniqueConstraints = @UniqueConstraint(columnNames = "ciudad"))
public class CiudadUbicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad_ubi", nullable = false)
    private Integer idCiudadUbi;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    public Integer getIdCiudadUbi() {
        return idCiudadUbi;
    }

    public void setIdCiudadUbi(Integer idCiudadUbi) {
        this.idCiudadUbi = idCiudadUbi;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

