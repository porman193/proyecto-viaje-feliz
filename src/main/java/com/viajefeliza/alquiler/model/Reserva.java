package com.viajefeliza.alquiler.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", nullable = false)
    private Integer idReserva;

    @Column(name = "comentarios_encuesta")
    private String comentariosEncuesta;

    @Column(name = "calif_encuesta")
    private Integer califEncuesta;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_ini", nullable = false)
    private Date fechaIni;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "precio_total", nullable = false)
    private Float precioTotal;

    @Column(name = "mascotas")
    private Boolean mascotas;

    @Column(name = "num_personas", nullable = false)
    private Integer numPersonas;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false)
    private Property property;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getComentariosEncuesta() {
        return comentariosEncuesta;
    }

    public void setComentariosEncuesta(String comentariosEncuesta) {
        this.comentariosEncuesta = comentariosEncuesta;
    }

    public Integer getCalifEncuesta() {
        return califEncuesta;
    }

    public void setCalifEncuesta(Integer califEncuesta) {
        this.califEncuesta = califEncuesta;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Boolean getMascotas() {
        return mascotas;
    }

    public void setMascotas(Boolean mascotas) {
        this.mascotas = mascotas;
    }

    public Integer getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

}

