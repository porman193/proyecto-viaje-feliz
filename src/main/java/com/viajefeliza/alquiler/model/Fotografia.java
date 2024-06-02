package com.viajefeliza.alquiler.model;
import jakarta.persistence.*;



@Entity
@Table(name = "fotografia")
public class Fotografia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fotografia", nullable = false)
    private Integer idFotografia;

    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false)
    private Property propiedad;

    @Lob
    @Column(name = "fotografia", nullable = false)
    private byte[] fotografia;

    public Integer getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(Integer idFotografia) {
        this.idFotografia = idFotografia;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public Property getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Property propiedad) {
        this.propiedad = propiedad;
    }
}
