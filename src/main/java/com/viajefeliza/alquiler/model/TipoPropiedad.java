package com.viajefeliza.alquiler.model;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo_propiedad", uniqueConstraints = @UniqueConstraint(columnNames = "tipo"))
public class TipoPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_propiedad", nullable = false)
    private Integer idTipoPropiedad;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    public Integer getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(Integer idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

