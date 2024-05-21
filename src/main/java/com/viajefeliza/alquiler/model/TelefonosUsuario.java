package com.viajefeliza.alquiler.model;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "telefonos_usuario")
public class TelefonosUsuario implements Serializable {

    @EmbeddedId
    private TelefonosUsuarioId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public TelefonosUsuarioId getId() {
        return id;
    }

    public void setId(TelefonosUsuarioId id) {
        this.id = id;
    }
}

@Embeddable
class TelefonosUsuarioId implements Serializable {

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "telefono")
    private String telefono;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}

