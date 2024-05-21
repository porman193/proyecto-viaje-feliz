package com.viajefeliza.alquiler.model;
import jakarta.persistence.*;

@Entity
@Table(name = "pais_ubicacion", uniqueConstraints = @UniqueConstraint(columnNames = "pais"))
public class PaisUbicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais_ubi", nullable = false)
    private Integer idPaisUbi;

    @Column(name = "pais", nullable = false)
    private String pais;

    public Integer getIdPaisUbi() {
        return idPaisUbi;
    }

    public void setIdPaisUbi(Integer idPaisUbi) {
        this.idPaisUbi = idPaisUbi;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}

