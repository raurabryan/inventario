package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.entidades.UnidadDeMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductosBDD {
	public ArrayList<Producto> buscar(String subcadena) throws KrakeDevException {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto producto = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select prod.codigo_pk, prod.nombre as nombre_producto, "
					+ "udm.nombre_udm, udm.descripcion_udm, cat.codigo_cat, cat.nombre as nombre_categoria, "
					+ "cast(prod.precio_venta as decimal(6,2)) as precio_venta, prod.tiene_iva, "
					+ "cast(prod.coste as decimal(5,4)) as coste, prod.stock " + "from producto prod "
					+ "join unidades_medida udm on prod.udm_fk = udm.nombre_udm "
					+ "join categorias cat on prod.categoria_fk = cat.codigo_cat " + "where upper(prod.nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("codigo_pk");
				String nombre = rs.getString("nombre_producto");
				String nombre_udm = rs.getString("nombre_udm");
				String descripcion_udm = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("codigo_cat");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				UnidadDeMedida udm = new UnidadDeMedida();
				udm.setNombre(nombre_udm);
				udm.setDescripcion(descripcion_udm);

				Categoria categoria = new Categoria();
				categoria.setCodigo(codigoCategoria);
				categoria.setNombre(nombreCategoria);

				producto = new Producto();
				producto.setCodigo(codigo);
				producto.setNombre(nombre);
				producto.setUnidadMedida(udm);
				producto.setPrecioVenta(precioVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(categoria);
				producto.setStock(stock);

				productos.add(producto);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle:" + e.getMessage());
		}
		return productos;
	}
	
	public void insertar (Producto producto ) throws KrakeDevException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("insert into producto (" +
		            "codigo_pk, nombre, udm_fk, categoria_fk, precio_venta, tiene_iva, coste, stock" +
		            ") values (?, ?, ?, ?, ?, ?, ?, ?)");
			 ps.setInt(1, producto.getCodigo());
		        ps.setString(2, producto.getNombre()); 
		        ps.setString(3, producto.getUnidadMedida().getNombre()); 
		        ps.setInt(4, producto.getCategoria().getCodigo()); 
		        ps.setBigDecimal(5, producto.getPrecioVenta());
		        ps.setBoolean(6, producto.isTieneIva()); 
		        ps.setBigDecimal(7, producto.getCoste()); 
		        ps.setInt(8, producto.getStock()); 
		        
		        ps.executeUpdate();
			
		}catch (KrakeDevException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar proveedores. Detalle"+e.getErrorCode());
		}
	}
	
}
