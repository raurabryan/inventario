package com.krakedev.inventario.servicios;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.krakedev.inventario.entidades.Producto;

@Path("productos")
public class ServiciosProducto {

    @Path("insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertar(Producto producto) {
        System.out.println("Producto insertado: " + producto);
        return Response.status(Response.Status.CREATED)
                       .entity("Producto insertado con éxito")
                       .build();
    }

    @Path("actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Producto producto) {
        System.out.println("Producto actualizado: " + producto);
        return Response.status(Response.Status.OK)
                       .entity("Producto actualizado con éxito")
                       .build();
    }

    @Path("recuperarTodos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)  
    public ArrayList<Producto> recuperarTodos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("1", "Leche", 2.5));
        productos.add(new Producto("2", "Pan", 1.5));
        productos.add(new Producto("3", "Huevos", 3.5));
        return productos;
    }
    ///////////////////////////////
    
    
    
 // Servicio para gestionar reservas
    @Path("reservas")
    public class ServicioReservas {

        @GET
        @Path("listar")
        @Produces(MediaType.APPLICATION_JSON)
        public Response listarReservas() {
            ReservasBDD reservasBDD = new ReservasBDD();
            ArrayList<Reserva> listaReservas;
            try {
                listaReservas = reservasBDD.obtenerTodas();
                return Response.ok(listaReservas).build();
            } catch (KrakeDevException e) {
                e.printStackTrace();
                return Response.serverError().entity(e.getMessage()).build();
            }
        }

        @POST
        @Path("agregar")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response agregarReserva(Reserva reserva) {
            ReservasBDD reservasBDD = new ReservasBDD();
            try {
                reservasBDD.insertar(reserva);
                return Response.ok().build();
            } catch (KrakeDevException e) {
                e.printStackTrace();
                return Response.serverError().entity(e.getMessage()).build();
            }
        }

        @DELETE
        @Path("cancelar/{id}")
        public Response cancelarReserva(@PathParam("id") int idReserva) {
            ReservasBDD reservasBDD = new ReservasBDD();
            try {
                reservasBDD.eliminar(idReserva);
                return Response.ok().build();
            } catch (KrakeDevException e) {
                e.printStackTrace();
                return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }

    // Clase ReservasBDD para gestionar reservas
    package com.krakedev.buses.bdd;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;

    import com.krakedev.buses.entidades.Reserva;
    import com.krakedev.buses.excepciones.KrakeDevException;
    import com.krakedev.utils.ConexionBDD;

    public class ReservasBDD {

        public ArrayList<Reserva> obtenerTodas() throws KrakeDevException {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList<Reserva> listaReservas = new ArrayList<>();
            try {
                con = ConexionBDD.obtenerConexion();
                ps = con.prepareStatement("select * from reservas");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Reserva reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_usuario"), rs.getInt("id_ruta"), rs.getDate("fecha_reserva"));
                    listaReservas.add(reserva);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new KrakeDevException("Error al obtener las reservas: " + e.getMessage(), e);
            } finally {
                ConexionBDD.cerrarConexion(con);
            }
            return listaReservas;
        }

        public void insertar(Reserva reserva) throws KrakeDevException {
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = ConexionBDD.obtenerConexion();
                ps = con.prepareStatement("insert into reservas (id_usuario, id_ruta, fecha_reserva) values (?, ?, ?)");
                ps.setInt(1, reserva.getIdUsuario());
                ps.setInt(2, reserva.getIdRuta());
                ps.setDate(3, new java.sql.Date(reserva.getFechaReserva().getTime()));
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new KrakeDevException("Error al insertar la reserva: " + e.getMessage(), e);
            } finally {
                ConexionBDD.cerrarConexion(con);
            }
        }

        public void eliminar(int idReserva) throws KrakeDevException {
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = ConexionBDD.obtenerConexion();
                ps = con.prepareStatement("delete from reservas where id_reserva = ?");
                ps.setInt(1, idReserva);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new KrakeDevException("Error al eliminar la reserva: " + e.getMessage(), e);
            } finally {
                ConexionBDD.cerrarConexion(con);
            }
        }
    }

    // Entidad Reserva
    package com.krakedev.buses.entidades;

    import java.util.Date;

    public class Reserva {
        private int id;
        private int idUsuario;
        private int idRuta;
        private Date fechaReserva;

        public Reserva(int id, int idUsuario, int idRuta, Date fechaReserva) {
            this.id = id;
            this.idUsuario = idUsuario;
            this.idRuta = idRuta;
            this.fechaReserva = fechaReserva;
        }

        public int getId() {
            return id;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public int getIdRuta() {
            return idRuta;
        }

        public Date getFechaReserva() {
            return fechaReserva;
        }
    }

}
