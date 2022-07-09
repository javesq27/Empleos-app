package com.proyecto.proyecto.entities;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "Vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private Date fecha;
    @Column
    private Double salario;
    @Column
    private Integer destacado;
    @Column
    private String imagen;
    @Column
    private String estatus;
    @Column
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
    
    
    
}
