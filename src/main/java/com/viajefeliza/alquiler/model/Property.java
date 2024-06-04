    package com.viajefeliza.alquiler.model;

    import java.io.Serializable;

    import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

    @Entity
    @Table(name = "propiedad")
    public class Property implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_propiedad", nullable = false)
        private Integer idPropiedad;

        @Column(name = "num_habitaciones", nullable = false)
        private Integer numHabitaciones;

        @Column(name = "precio_base", nullable = false)
        private Float precioBase;

        @Column(name = "acepta_mascotas")
        private Boolean aceptaMascotas;

        @Column(name = "num_banos", nullable = false)
        private Integer numBanos;

        @Column(name = "calefaccion")
        private Boolean calefaccion;

        @Column(name = "aire_acondicionado")
        private Boolean aireAcondicionado;

        @Column(name = "ubicacion_direccion", nullable = false, length = 255)
        private String ubicacionDireccion;

        @Column(name = "capacidad", nullable = false)
        private Integer capacidad;

        @ManyToOne
        @JoinColumn(name = "id_tipo_propiedad", nullable = false)
        private TipoPropiedad tipoPropiedad;

        @ManyToOne
        @JoinColumn(name = "id_pais_ubi", nullable = false)
        private PaisUbicacion paisUbicacion;

        @ManyToOne
        @JoinColumn(name = "id_ciudad_ubi", nullable = false)
        private CiudadUbicacion ciudadUbicacion;

        @ManyToOne
        @JoinColumn(name = "id_region_ubi", nullable = false)
        private RegionUbicacion regionUbicacion;


        public Integer getIdPropiedad() {
            return idPropiedad;
        }

        public void setIdPropiedad(Integer idPropiedad) {
            this.idPropiedad = idPropiedad;
        }

        public Integer getNumHabitaciones() {
            return numHabitaciones;
        }

        public void setNumHabitaciones(Integer numHabitaciones) {
            this.numHabitaciones = numHabitaciones;
        }

        public Float getPrecioBase() {
            return precioBase;
        }

        public void setPrecioBase(Float precioBase) {
            this.precioBase = precioBase;
        }

        public Boolean getAceptaMascotas() {
            return aceptaMascotas;
        }

        public void setAceptaMascotas(Boolean aceptaMascotas) {
            this.aceptaMascotas = aceptaMascotas;
        }

        public Integer getNumBanos() {
            return numBanos;
        }

        public void setNumBanos(Integer numBanos) {
            this.numBanos = numBanos;
        }

        public Boolean getCalefaccion() {
            return calefaccion;
        }

        public void setCalefaccion(Boolean calefaccion) {
            this.calefaccion = calefaccion;
        }

        public Boolean getAireAcondicionado() {
            return aireAcondicionado;
        }

        public void setAireAcondicionado(Boolean aireAcondicionado) {
            this.aireAcondicionado = aireAcondicionado;
        }

        public String getUbicacionDireccion() {
            return ubicacionDireccion;
        }

        public void setUbicacionDireccion(String ubicacionDireccion) {
            this.ubicacionDireccion = ubicacionDireccion;
        }

        public Integer getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(Integer capacidad) {
            this.capacidad = capacidad;
        }

        public TipoPropiedad getTipoPropiedad() {
            return tipoPropiedad;
        }

        public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
            this.tipoPropiedad = tipoPropiedad;
        }

        public PaisUbicacion getPaisUbicacion() {
            return paisUbicacion;
        }

        public void setPaisUbicacion(PaisUbicacion paisUbicacion) {
            this.paisUbicacion = paisUbicacion;
        }

        public CiudadUbicacion getCiudadUbicacion() {
            return ciudadUbicacion;
        }

        public void setCiudadUbicacion(CiudadUbicacion ciudadUbicacion) {
            this.ciudadUbicacion = ciudadUbicacion;
        }

        public RegionUbicacion getRegionUbicacion() {
            return regionUbicacion;
        }

        public void setRegionUbicacion(RegionUbicacion regionUbicacion) {
            this.regionUbicacion = regionUbicacion;
        }

        public Property() {
        }


    }
