package com.proyecto.proyecto.entities;

import com.proyecto.proyecto.services.Observador;
import com.proyecto.proyecto.services.ServicioMails;
import com.proyecto.proyecto.services.SujetoObservado;

import java.util.Date;
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


	public Solicitud() {
	}

	public Solicitud(Date fecha) {
		this.fecha = new Date();
	}

	public Solicitud(Integer id, Date fecha, String comentarios, String archivo, Vacante vacante, Usuario usuario, boolean revisada) {
		this.id = id;
		this.fecha = fecha;
		this.comentarios = comentarios;
		this.archivo = archivo;
		this.vacante = vacante;
		this.usuario = usuario;
		this.revisada = revisada;
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


	@Override
	public void notificar(ServicioMails servicioMails) {
		this.usuario.onUpdate(servicioMails);
	}
}
