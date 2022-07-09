package com.proyecto.proyecto.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "Usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String username;
    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Integer estatus;
    @Column
    private Date fechaRegistro;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioPerfil",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil")
    )
    private List<Perfil> perfiles;

    public void agregar(Perfil tempPerfil) {
        if(perfiles == null) {
            perfiles = new LinkedList<Perfil>();
        }
        perfiles.add(tempPerfil);
    }

    public List<Perfil> getPerfiles() {
        return this.perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstatus() {
        return this.estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
