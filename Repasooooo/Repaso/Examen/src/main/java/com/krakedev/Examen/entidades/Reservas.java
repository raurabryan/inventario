package com.krakedev.Examen.entidades;

public class Reservas {
	private int idReserva;
	private Usuarios usuario;
	private Rutas ruta;
	public Reservas(int idReserva, Usuarios usuario, Rutas ruta) {
		super();
		this.idReserva = idReserva;
		this.usuario = usuario;
		this.ruta = ruta;
	}
	public Reservas() {
		super();
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public Rutas getRuta() {
		return ruta;
	}
	public void setRuta(Rutas ruta) {
		this.ruta = ruta;
	}
	@Override
	public String toString() {
		return "Reservas [idReserva=" + idReserva + ", usuario=" + usuario + ", ruta=" + ruta + "]";
	}
	
}
