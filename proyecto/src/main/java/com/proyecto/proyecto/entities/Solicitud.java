package com.proyecto.proyecto.entities;

import com.proyecto.proyecto.services.Observador;
import com.proyecto.proyecto.services.SujetoObservado;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import javax.persistence.*;


@Table(name = "Solicitudes")
@Entity
public class Solicitud implements SujetoObservado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	@Column
	private Date fecha;
	@Column
	private String comentarios;
	@Column
	private String archivo;
	
	@OneToOne
	@JoinColumn(name = "idVacante")
	private Vacante vacante;

	@OneToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean revisada;

	private ArrayList<Observador> listaObservador;

	public Solicitud() {
		this.listaObservador=new ArrayList<>();

	}

	public Solicitud(Date fecha) {
		this.fecha = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Vacante getVacante() {
		return vacante;
	}

	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public boolean isRevisada() {
		return revisada;
	}

	public void setRevisada(boolean revisada) {
		this.revisada = revisada;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", fecha=" + fecha + ", comentarios=" + comentarios + ", archivo=" + archivo
				+ ", vacante=" + vacante + ", usuario=" + usuario +", revisada= " + revisada +"]";
	}

	public ArrayList<Observador> getObservador() {
		return listaObservador;
	}

	public void setObservador(Observador observador) {
		listaObservador = new ArrayList<>();
		listaObservador.add(observador);
	}

	@Override
	public void notificar() {
		for(Observador o:listaObservador){
			o.onUpdate();
		}
	}
}
