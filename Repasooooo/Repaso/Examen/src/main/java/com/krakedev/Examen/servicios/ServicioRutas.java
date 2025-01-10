package com.krakedev.Examen.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.Examen.bdd.RutasBdd;
import com.krakedev.Examen.entidades.Rutas;
import com.krakedev.Examen.excepciones.KrakedevException;


@Path("rutas")
public class ServicioRutas {
	@Path("buscar")
	@GET
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public Response buscar(){
		RutasBdd rutaBdd= new RutasBdd();
		ArrayList<Rutas> buses = null;
		try {
			buses = rutaBdd.buscar();
			return Response.ok(buses).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
