package com.krakedev.inventario.entidades;

public class UnidadDeMedida {
	private String nombre;
	private String descripcion;
	private CategoriaUDM categoriaUnidadMedida;
	
	public UnidadDeMedida() {
		
	}
	
	public UnidadDeMedida(String nombre, String descripcion, CategoriaUDM categoriaUnidadMedida) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoriaUnidadMedida = categoriaUnidadMedida;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public CategoriaUDM getCategoriaUnidadMedida() {
		return categoriaUnidadMedida;
	}
	public void setCategoriaUnidadMedida(CategoriaUDM categoriaUnidadMedida) {
		this.categoriaUnidadMedida = categoriaUnidadMedida;
	}

	@Override
	public String toString() {
		return "UnidadDeMedida [nombre=" + nombre + ", descripcion=" + descripcion + ", categoriaUnidadMedida="
				+ categoriaUnidadMedida + "]";
	}
	
	
}
