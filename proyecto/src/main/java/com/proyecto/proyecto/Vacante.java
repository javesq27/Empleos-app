package com.proyecto.proyecto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "Vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private Double salario;
    private Integer destacado;
    private String imagen ="no-image.png";
    private String estatus;
    private String detalles;
    @OneToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }
    public Double getSalario() {
        return salario;
    }
    public void setDestacado(Integer destacado) {
        this.destacado = destacado;
    }
    public Integer getDestacado() {
        return destacado;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getImagen() {
        return imagen;
    } 
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    public String getEstatus() {
        return estatus;
    }
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    public String getDetalles() {
        return detalles;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void reset() {
        this.imagen = null;
    }


    public String toString() {
        return "Vacante [id=" + id + ", nombre= " + nombre + ", descripcion= " + descripcion + ", fecha= " + fecha + 
        ", destacado= " + destacado + ", imagen= " + imagen + ", salario= " + salario + ", categoria= " + categoria + "estatus= " + estatus + ", detalles= " + detalles + "]";
    }
    public String setImagen(Boolean nombreImagen) {
        return null;
    }
    
    
}
