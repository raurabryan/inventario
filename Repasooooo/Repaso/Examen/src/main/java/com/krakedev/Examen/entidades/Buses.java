package com.krakedev.Examen.entidades;

public class Buses {
	private String placa;
	private int capacidadMaxima;
	public Buses(String placa, int capacidadMaxima) {
		super();
		this.placa = placa;
		this.capacidadMaxima = capacidadMaxima;
	}
	public Buses() {}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}
	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}
	@Override
	public String toString() {
		return "Buses [placa=" + placa + ", capacidadMaxima=" + capacidadMaxima + "]";
	}
	
}
