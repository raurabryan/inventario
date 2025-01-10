package com.krakedev.Examen.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.Examen.entidades.Buses;
import com.krakedev.Examen.excepciones.KrakedevException;
import com.krakedev.Examen.utils.ConexionBDD;


public class BusesBdd {
	public ArrayList<Buses> buscar() throws KrakedevException {
		ArrayList<Buses> buses = new ArrayList<Buses>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Buses bus = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select * from buses");
			rs = ps.executeQuery();
			while (rs.next()) {
				String placa = rs.getString("placa");
				int capacidadMaxima = rs.getInt("cap_max");
				bus=new Buses(placa,capacidadMaxima);
				buses.add(bus);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar Productos. Detalle: " + e.getMessage());
		}
		return buses;
	}

}
