package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventarios.bdd.TipoDocumentoBDD;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("tiposdocumentos")
public class ServiciosTipoDocumento {
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(){
		TipoDocumentoBDD tdBDD = new TipoDocumentoBDD();
   	 ArrayList<TipoDocumento> tipoDocumentos = null;
		try {
			tipoDocumentos = tdBDD.buscar();
			return Response.ok(tipoDocumentos).build();
		} catch (KrakeDevException e) {
			
			e.printStackTrace();
			return Response.serverError().build();
			
		}
	}
}
