package com.krakedev.Examen.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.Examen.bdd.BusesBdd;
import com.krakedev.Examen.entidades.Buses;
import com.krakedev.Examen.excepciones.KrakedevException;


@Path("buses")
public class ServicioBuses {
	@Path("buscar")
	@GET
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public Response buscar(){
		BusesBdd busBDD= new BusesBdd();
		ArrayList<Buses> buses = null;
		try {
			buses = busBDD.buscar();
			return Response.ok(buses).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
