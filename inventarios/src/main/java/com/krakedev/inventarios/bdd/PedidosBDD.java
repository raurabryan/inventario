package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.DetallePedido;
import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {
	public void insertar (Pedido pedido ) throws KrakeDevException{
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		ResultSet rsClave = null;
		int codigoCabecera =0;
		
		LocalDate fechaActual = LocalDate.now();
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("insert into cabecera_pedido (proveedor_fk, fecha, estado_fk) values "
					+ "(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setObject(2, fechaActual);
			ps.setString(3, "S");
			ps.executeUpdate();
			
			rsClave=ps.getGeneratedKeys();
			
			if (rsClave.next()) {
				codigoCabecera=rsClave.getInt(1);
				
			}
			
			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();
			DetallePedido det;
			for (int i=0; i< detallesPedido.size();i++) {
				det = detallesPedido.get(i);
				psDet= con.prepareStatement("insert into detalle_pedido (cabecera_pedido_fk, producto_fk, cantidad_solicitada, subtotal, cantidad_recibida) values "
						+ "(?, ?, ?, ?, ?)");
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidadSolicitada());
				psDet.setInt(5, 0);
				BigDecimal pv=det.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getCantidadSolicitada());
				BigDecimal subtotal = pv.multiply(cantidad);
				psDet.setBigDecimal(4, subtotal);
				
				psDet.executeUpdate();
				
			}
		}catch (KrakeDevException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar proveedores. Detalle"+e.getErrorCode());
		}
	}
	
}