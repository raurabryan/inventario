package com.krakedev.inventarios.excepciones;

import java.sql.SQLException;

public class KrakeDevException extends Exception {
	public KrakeDevException(String mensaje, SQLException e) {
		super(mensaje);
		
	}

	public KrakeDevException(String string) {
		// TODO Auto-generated constructor stub
	}
}
