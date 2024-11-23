package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	public ArrayList<Proveedor> buscar(String subcadena) throws KrakeDevException {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select prov.identificador_pk,prov.tipo_documento,td.descripcion,prov.nombre,prov.telefono,prov.correo,prov.direccion "
					+ " from proveedores prov, tipo_documentos td "
					+ " where prov.tipo_documento = td.codigo "
					+ "and upper (nombre)  like '%A%'"
			);
			ps.setString(1, "%"+subcadena.toUpperCase()+"%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador_pk");
				String codigoTipoDocumento = rs.getString("tipo_documento");
				String descripcionTipoDocumento = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				TipoDocumento td= new TipoDocumento(codigoTipoDocumento, descripcionTipoDocumento);
				
				
				proveedor = new Proveedor(identificador,td,nombre,telefono,correo,direccion);
				proveedores.add(proveedor);

			}
		} catch (KrakeDevException e) {

			e.printStackTrace();
			throw e;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle:" + e.getMessage());
		}

		return proveedores;
	}
	
	public void insertar (Proveedor proveedor ) throws KrakeDevException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("insert into proveedores (identificador_pk, tipo_documento, nombre, telefono, correo, direccion)"
					+ " values (?,?,?,?,?,?);");
			ps.setString(1, proveedor.getIdentificador());
			ps.setString(2, proveedor.getTipoDocumento().getCodigo());
			ps.setString(3, proveedor.getNombre());
			ps.setString(4, proveedor.getTelefono());
			ps.setString(5, proveedor.getCorreo());
			ps.setString(6, proveedor.getDireccion());
			ps.executeUpdate();
			
		}catch (KrakeDevException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar proveedores. Detalle"+e.getErrorCode());
		}
	}
	
	
	
	
	
	
}
