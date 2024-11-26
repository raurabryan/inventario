package com.krakedev.inventarios.bdd;

import com.krakedev.inventarios.utils.ConexionBDD;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventario.entidades.Ventas;
import com.krakedev.inventario.entidades.DetalleVentas;

import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class VentasBDD {

    
	public void actualizarCabecera(int id, Ventas venta) throws KrakeDevException {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = ConexionBDD.obtenerConexion();
	        ps = con.prepareStatement(
	            "update cabecera_ventas set fecha = ?, total_sin_iva = ?, iva = ?, total = ? where codigo = ?"
	        );

	        ps.setObject(1, venta.getFecha());  
	        ps.setBigDecimal(2, venta.getTotalSinIva());  
	        ps.setBigDecimal(3, venta.getIva());  
	        ps.setBigDecimal(4, venta.getTotal());  
	        ps.setInt(5, id);  

	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated == 0) {
	            throw new KrakeDevException("No se encontró una venta con el ID especificado.");
	        }

	    } catch (SQLException e) {
	        throw new KrakeDevException("Error al actualizar la venta. Detalle: " + e.getMessage());
	    } finally {
	        ConexionBDD.cerrarConexion(con, ps, null);
	    }
	}

   
	public void insertar(Ventas ventas) throws KrakeDevException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    PreparedStatement psDet = null;
	    ResultSet rsClave = null;

	    try {
	        con = ConexionBDD.obtenerConexion();
	        con.setAutoCommit(false);

	        ps = con.prepareStatement(
	            "insert into cabecera_ventas (fecha, total_sin_iva, iva, total) values (?, ?, ?, ?)",
	            Statement.RETURN_GENERATED_KEYS
	        );

	        ps.setObject(1, LocalDate.now());
	        ps.setBigDecimal(2, BigDecimal.ZERO);  
	        ps.setBigDecimal(3, BigDecimal.ZERO);  
	        ps.setBigDecimal(4, BigDecimal.ZERO);  
	        ps.executeUpdate();

	        rsClave = ps.getGeneratedKeys();
	        int codigoCabecera = 0;
	        if (rsClave.next()) {
	            codigoCabecera = rsClave.getInt(1);
	        }

	        DetalleVentas detalle = ventas.getDetalles().get(0); 

	        psDet = con.prepareStatement(
	            "insert into detalle_ventas (cabecera_ventas_fk, producto_fk, cantidad, precio_venta, subtotal, subtotal_con_iva) values (?, ?, ?, ?, ?, ?)"
	        );

	        BigDecimal precioVenta = detalle.getProducto().getPrecioVenta();
	        BigDecimal cantidad = BigDecimal.valueOf(detalle.getCantidad());
	        BigDecimal subtotal = precioVenta.multiply(cantidad);
	        BigDecimal subtotalConIva = detalle.getProducto().isTieneIva()
	            ? subtotal.multiply(BigDecimal.valueOf(1.12)) 
	            : subtotal;

	        psDet.setInt(1, codigoCabecera);  
	        psDet.setInt(2, detalle.getProducto().getCodigo());  
	        psDet.setInt(3, detalle.getCantidad());  
	        psDet.setBigDecimal(4, precioVenta); 
	        psDet.setBigDecimal(5, subtotal); 
	        psDet.setBigDecimal(6, subtotalConIva);  
	        psDet.executeUpdate();

	        con.commit(); 

	    } catch (SQLException e) {
	        if (con != null) {
	            try {
	                con.rollback(); 
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        throw new KrakeDevException("Error al insertar la venta. Detalle: " + e.getMessage());
	    } finally {
	        ConexionBDD.cerrarConexion(con, ps, rsClave);
	    }
	}

   
	public void registrarMovimientoStock(int idVenta) throws KrakeDevException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    PreparedStatement psHistorial = null;
	    ResultSet rsDetalles = null;

	    try {
	        con = ConexionBDD.obtenerConexion();

	        ps = con.prepareStatement(
	            "select producto_fk, cantidad FROM detalle_ventas where cabecera_ventas_fk = ?"
	        );

	        ps.setInt(1, idVenta);
	        rsDetalles = ps.executeQuery();

	        if (rsDetalles.next()) {
	            int codigoProducto = rsDetalles.getInt("producto_fk"); 
	            int cantidad = rsDetalles.getInt("cantidad"); 

	            psHistorial = con.prepareStatement(
	                "insert into historial_stock (fecha, referencia, producto_fk, cantidad) values (?, ?, ?, ?)"
	            );

	            psHistorial.setObject(1, LocalDate.now());  
	            psHistorial.setString(2, "VENTA " + idVenta);  
	            psHistorial.setInt(3, codigoProducto); 
	            psHistorial.setInt(4, -cantidad);  
	            psHistorial.executeUpdate();
	        }

	    } catch (SQLException e) {
	        throw new KrakeDevException("Error al registrar el movimiento de stock. Detalle: " + e.getMessage());
	    } finally {
	        ConexionBDD.cerrarConexion(con, ps, rsDetalles);
	    }
	}
}
