package com.proyecto.proyecto.entities;

import javax.persistence.*;

@Entity
@Table(name = "Perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String perfil;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    public String getPerfil() {
        return perfil;
    }
}