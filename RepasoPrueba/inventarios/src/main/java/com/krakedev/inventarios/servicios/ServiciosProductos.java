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

import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("productos")
public class ServiciosProductos {

	@Path("buscarproducto/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {
		ProductosBDD proBDD = new ProductosBDD();
		ArrayList<Producto> productos = null;
		try {
			productos = proBDD.buscar(subcadena);
			return Response.ok(productos).build();
		} catch (KrakeDevException e) {

			e.printStackTrace();
			return Response.serverError().build();

		}
	}

	@Path("crearproducto")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Producto producto) {
		ProductosBDD proBDD = new ProductosBDD();
		try {
			proBDD.insertar(producto);
			return Response.ok().build();
		} catch (KrakeDevException e) {

			e.printStackTrace();
			return Response.serverError().build();

		}
	}

	@PUT
	@Path("actualizarproducto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizarProducto(Producto producto) {
		ProductosBDD productoBDD = new ProductosBDD();
		try {
			productoBDD.actualizar(producto);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("buscarproductoporid/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarProducto(@PathParam("codigo") int codigo) {
		ProductosBDD productoBDD = new ProductosBDD();
		try {
			Producto producto = productoBDD.buscarPorCodigo(codigo);
			if (producto != null) {
				return Response.ok(producto).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	
}
