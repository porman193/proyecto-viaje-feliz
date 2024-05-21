package com.viajefeliza.alquiler.model;
import jakarta.persistence.*;



@Entity
@Table(name = "region_ubicacion", uniqueConstraints = @UniqueConstraint(columnNames = "region"))
public class RegionUbicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region_ubi", nullable = false)
    private Integer idRegionUbi;

    @Column(name = "region", nullable = false)
    private String region;

    public Integer getIdRegionUbi() {
        return idRegionUbi;
    }

    public void setIdRegionUbi(Integer idRegionUbi) {
        this.idRegionUbi = idRegionUbi;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

