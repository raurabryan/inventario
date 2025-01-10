package com.krakedev.inventarios.servicios;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventarios.bdd.CategoriaBDD;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("/categorias")
public class ServiciosCategoria {

    private CategoriaBDD categoriaBDD = new CategoriaBDD();

   
    @Path("/registrarcategoria")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCategoria(Categoria categoria) {
        try {
            categoriaBDD.insertar(categoria);
            return Response.ok().build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

  
    @Path("/actualizarcategoria")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCategoria(Categoria categoria) {
        try {
            categoriaBDD.actualizarCategoria(categoria);
            return Response.ok().build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    
    @GET
    @Path("/listarcategorias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarCategorias() {
        try {
            List<Categoria> categorias = categoriaBDD.recuperarCategorias();
            return Response.ok(categorias).build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
