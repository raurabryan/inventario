package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventarios.bdd.PedidosBDD;
import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("pedidos")
public class ServicioPedidos {
	@Path("registrar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Pedido pedido) {
		PedidosBDD pedidoBDD = new PedidosBDD();
		try {
			pedidoBDD.insertar(pedido);
			return Response.ok().build();
		} catch (KrakeDevException e) {

			e.printStackTrace();
			return Response.serverError().build();

		}
	}

	@Path("actualizarPedido")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarPedido(Pedido pedido) {
		PedidosBDD pedidoBDD = new PedidosBDD();
		try {
			pedidoBDD.actualizarEstadoPedido(pedido);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Path("proveedorpedidos/{idProveedor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPedidosPorProveedor(@PathParam("idProveedor") String idProveedor) {
		ProductosBDD productoBDD = new ProductosBDD(); 
		ArrayList<Producto> productos;

		try {
			productos = productoBDD.buscarPorProveedor(idProveedor);
			return Response.ok(productos).build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
