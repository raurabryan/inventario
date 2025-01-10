package com.krakedev.inventario.entidades;

public class CategoriaUDM {
	private String codigo;
	private String producto;
	
	public CategoriaUDM() {
		
	}
	
	public CategoriaUDM(String codigo, String producto) {
		super();
		this.codigo = codigo;
		this.producto = producto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "CategoriaUDM [codigo=" + codigo + ", producto=" + producto + "]";
	}
	
	
}
