package com.krakedev.Examen.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.krakedev.Examen.entidades.Rutas;
import com.krakedev.Examen.excepciones.KrakedevException;
import com.krakedev.Examen.utils.ConexionBDD;

public class RutasBdd {
	public ArrayList<Rutas> buscar() throws KrakedevException {
		ArrayList<Rutas> rutas = new ArrayList<Rutas>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Rutas ruta = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select * from rutas");
			rs = ps.executeQuery();
			while (rs.next()) {
				int placa = rs.getInt("id_ruta");
				String origen = rs.getString("origen");
				String destino = rs.getString("destino");
				Time horario = rs.getTime("horario");
				ruta=new Rutas(placa,origen,destino,horario);
				rutas.add(ruta);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar Productos. Detalle: " + e.getMessage());
		}
		return rutas;
	}

}
