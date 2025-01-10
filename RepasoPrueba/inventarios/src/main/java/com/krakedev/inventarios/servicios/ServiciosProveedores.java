package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("proveedores")
public class ServiciosProveedores {
	
	
	@Path("buscarproveedores/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena){
		ProveedoresBDD provBDD = new ProveedoresBDD();
   	 ArrayList<Proveedor> proveedores = null;
		try {
			proveedores = provBDD.buscar(subcadena);
			return Response.ok(proveedores).build();
		} catch (KrakeDevException e) {
			
			e.printStackTrace();
			return Response.serverError().build();
			
		}
	}
	
	@Path("crearproveedor")
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	public Response crear (Proveedor proveedor) {
		ProveedoresBDD provBDD = new ProveedoresBDD();
		try {
			provBDD.insertar(proveedor);
			return Response.ok().build();
		}catch (KrakeDevException e) {
			
			e.printStackTrace();
			return Response.serverError().build();
			
		}
	}
	
	@GET
    @Path("buscarproveedor/{idProveedor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProveedor(@PathParam("idProveedor") String idProveedor) {
        ProveedoresBDD proveedorBDD = new ProveedoresBDD();
        
        try {
            Proveedor proveedor = proveedorBDD.buscarPorId(idProveedor);
            if (proveedor != null) {
                return Response.ok(proveedor).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Proveedor no encontrado").build();
            }
        } catch (KrakeDevException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
