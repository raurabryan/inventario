package com.krakedev.Examen.entidades;

import java.sql.Time;

public class Rutas {
	private int idRuta;
	private String origen;
	private String destino;
	private Time horario;
	public Rutas(int idRuta, String origen, String destino, Time horario) {
		super();
		this.idRuta = idRuta;
		this.origen = origen;
		this.destino = destino;
		this.horario = horario;
	}
	public Rutas() {
		super();
	}
	public int getIdRuta() {
		return idRuta;
	}
	public void setIIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Time getHorario() {
		return horario;
	}
	public void setHorario(Time horario) {
		this.horario = horario;
	}
	@Override
	public String toString() {
		return "Usuarios [idRuta=" + idRuta + ", origen=" + origen + ", destino=" + destino + ", horario="
				+ horario + "]";
	}
	
}
