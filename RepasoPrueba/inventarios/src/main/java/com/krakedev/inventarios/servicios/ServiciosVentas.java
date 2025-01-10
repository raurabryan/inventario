package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.entidades.Ventas;
import com.krakedev.inventarios.bdd.VentasBDD;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("ventas")

public class ServiciosVentas {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearVenta(Ventas venta) {
        VentasBDD ventasBDD = new VentasBDD();
        try {
            ventasBDD.insertar(venta);
            return Response.ok("Venta creada exitosamente.").build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.serverError().entity("Error al crear la venta: " + e.getMessage()).build();
        }
    }

	@PUT
	@Path("actualizar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizarVenta(@PathParam("id") int id, Ventas venta) {
	    VentasBDD ventasBDD = new VentasBDD();
	    try {
	        ventasBDD.actualizarCabecera(id, venta);
	        return Response.ok().build();
	    } catch (KrakeDevException e) {
	        e.printStackTrace(); 
	        return Response.serverError().entity("Error al actualizar la venta: " + e.getMessage()).build();
	    }
	}

    @POST
    @Path("registrarMovimientoStock/{id}")
    public Response registrarMovimientoStock(@PathParam("id") int id) {
        VentasBDD ventasBDD = new VentasBDD();
        try {
            ventasBDD.registrarMovimientoStock(id);
            return Response.ok("Movimiento de stock registrado exitosamente.").build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.serverError().entity("Error al registrar el movimiento de stock: " + e.getMessage()).build();
        }
    }
}
