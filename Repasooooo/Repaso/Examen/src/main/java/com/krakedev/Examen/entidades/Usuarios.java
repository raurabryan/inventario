package com.krakedev.Examen.entidades;

public class Usuarios {
	private int idUsuario;
	private String nombre;
	private String correo;
	public Usuarios(int idUsuario, String nombre, String correo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.correo = correo;
	}
	public Usuarios() {
		super();
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", nombre=" + nombre + ", correo=" + correo + "]";
	}
	
}
