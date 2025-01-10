package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.entidades.Categoria;


@Path("categorias")
public class ServiciosCategoria {

	@Path("insertar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Categoria categoria) {
		System.out.println("Categoria insertado: " + categoria);
		return Response.status(Response.Status.CREATED).entity("Categoria insertado con éxito").build();
	}

	//////////
	
	package com.krakedev.buses.servicios;

	import java.util.ArrayList;

	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;

	import com.krakedev.buses.bdd.BusesBDD;
	import com.krakedev.buses.bdd.RutasBDD;
	import com.krakedev.buses.entidades.Bus;
	import com.krakedev.buses.entidades.Ruta;
	import com.krakedev.buses.excepciones.KrakeDevException;

	@Path("buses")
	public class ServicioBuses {

	    @GET
	    @Path("listar")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response listarBuses() {
	        BusesBDD busesBDD = new BusesBDD();
	        ArrayList<Bus> listaBuses;
	        try {
	            listaBuses = busesBDD.obtenerTodos();
	            return Response.ok(listaBuses).build();
	        } catch (KrakeDevException e) {
	            e.printStackTrace();
	            return Response.serverError().entity(e.getMessage()).build();
	        }
	    }
	}

	@Path("rutas")
	public class ServicioRutas {

	    @GET
	    @Path("listar")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response listarRutas() {
	        RutasBDD rutasBDD = new RutasBDD();
	        ArrayList<Ruta> listaRutas;
	        try {
	            listaRutas = rutasBDD.obtenerTodas();
	            return Response.ok(listaRutas).build();
	        } catch (KrakeDevException e) {
	            e.printStackTrace();
	            return Response.serverError().entity(e.getMessage()).build();
	        }
	    }
	}

	// Clase BusesBDD para acceder a la base de datos y obtener todos los buses
	package com.krakedev.buses.bdd;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import com.krakedev.buses.entidades.Bus;
	import com.krakedev.buses.excepciones.KrakeDevException;
	import com.krakedev.utils.ConexionBDD;

	public class BusesBDD {
	    public ArrayList<Bus> obtenerTodos() throws KrakeDevException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Bus> listaBuses = new ArrayList<>();
	        try {
	            con = ConexionBDD.obtenerConexion();
	            ps = con.prepareStatement("select * from buses");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Bus bus = new Bus(rs.getInt("id_bus"), rs.getString("numero_bus"), rs.getInt("capacidad"));
	                listaBuses.add(bus);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new KrakeDevException("Error al obtener los buses: " + e.getMessage(), e);
	        } finally {
	            ConexionBDD.cerrarConexion(con);
	        }
	        return listaBuses;
	    }
	}

	// Clase RutasBDD para acceder a la base de datos y obtener todas las rutas
	package com.krakedev.buses.bdd;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import com.krakedev.buses.entidades.Ruta;
	import com.krakedev.buses.excepciones.KrakeDevException;
	import com.krakedev.utils.ConexionBDD;

	public class RutasBDD {
	    public ArrayList<Ruta> obtenerTodas() throws KrakeDevException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Ruta> listaRutas = new ArrayList<>();
	        try {
	            con = ConexionBDD.obtenerConexion();
	            ps = con.prepareStatement("select * from rutas");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Ruta ruta = new Ruta(rs.getInt("id_ruta"), rs.getString("ciudad_origen"), rs.getString("ciudad_destino"), rs.getTime("horario_salida"), rs.getTime("horario_llegada"));
	                listaRutas.add(ruta);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new KrakeDevException("Error al obtener las rutas: " + e.getMessage(), e);
	        } finally {
	            ConexionBDD.cerrarConexion(con);
	        }
	        return listaRutas;
	    }
	}

	// Entidad Bus
	package com.krakedev.buses.entidades;

	public class Bus {
	    private int id;
	    private String numeroBus;
	    private int capacidad;

	    public Bus(int id, String numeroBus, int capacidad) {
	        this.id = id;
	        this.numeroBus = numeroBus;
	        this.capacidad = capacidad;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getNumeroBus() {
	        return numeroBus;
	    }

	    public int getCapacidad() {
	        return capacidad;
	    }
	}

	// Entidad Ruta
	package com.krakedev.buses.entidades;

	import java.sql.Time;

	public class Ruta {
	    private int id;
	    private String ciudadOrigen;
	    private String ciudadDestino;
	    private Time horarioSalida;
	    private Time horarioLlegada;

	    public Ruta(int id, String ciudadOrigen, String ciudadDestino, Time horarioSalida, Time horarioLlegada) {
	        this.id = id;
	        this.ciudadOrigen = ciudadOrigen;
	        this.ciudadDestino = ciudadDestino;
	        this.horarioSalida = horarioSalida;
	        this.horarioLlegada = horarioLlegada;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getCiudadOrigen() {
	        return ciudadOrigen;
	    }

	    public String getCiudadDestino() {
	        return ciudadDestino;
	    }

	    public Time getHorarioSalida() {
	        return horarioSalida;
	    }

	    public Time getHorarioLlegada() {
	        return horarioLlegada;
	    }
	}
	/////////////////////////////


	package com.krakedev.buses.servicios;

	import java.util.ArrayList;

	import javax.ws.rs.Consumes;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;

	import com.krakedev.buses.bdd.BusesBDD;
	import com.krakedev.buses.bdd.RutasBDD;
	import com.krakedev.buses.entidades.Bus;
	import com.krakedev.buses.entidades.Ruta;
	import com.krakedev.buses.excepciones.KrakeDevException;

	@Path("buses")
	public class ServicioBuses {

	    @GET
	    @Path("listar")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response listarBuses() {
	        BusesBDD busesBDD = new BusesBDD();
	        ArrayList<Bus> listaBuses;
	        try {
	            listaBuses = busesBDD.obtenerTodos();
	            return Response.ok(listaBuses).build();
	        } catch (KrakeDevException e) {
	            e.printStackTrace();
	            return Response.serverError().entity(e.getMessage()).build();
	        }
	    }

	    @POST
	    @Path("agregar")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response agregarBus(Bus bus) {
	        BusesBDD busesBDD = new BusesBDD();
	        try {
	            busesBDD.insertar(bus);
	            return Response.ok().build();
	        } catch (KrakeDevException e) {
	            e.printStackTrace();
	            return Response.serverError().entity(e.getMessage()).build();
	        }
	    }
	}

	@Path("rutas")
	public class ServicioRutas {

	    @GET
	    @Path("listar")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response listarRutas() {
	        RutasBDD rutasBDD = new RutasBDD();
	        ArrayList<Ruta> listaRutas;
	        try {
	            listaRutas = rutasBDD.obtenerTodas();
	            return Response.ok(listaRutas).build();
	        } catch (KrakeDevException e) {
	            e.printStackTrace();
	            return Response.serverError().entity(e.getMessage()).build();
	        }
	    }

	    @POST
	    @Path("agregar")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response agregarRuta(Ruta ruta) {
	        RutasBDD rutasBDD = new RutasBDD();
	        try {
	            rutasBDD.insertar(ruta);
	            return Response.ok().build();
	        } catch (KrakeDevException e) {
	            e.printStackTrace();
	            return Response.serverError().entity(e.getMessage()).build();
	        }
	    }
	}

	package com.krakedev.buses.bdd;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import com.krakedev.buses.entidades.Bus;
	import com.krakedev.buses.excepciones.KrakeDevException;
	import com.krakedev.utils.ConexionBDD;

	public class BusesBDD {
	    public ArrayList<Bus> obtenerTodos() throws KrakeDevException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Bus> listaBuses = new ArrayList<>();
	        try {
	            con = ConexionBDD.obtenerConexion();
	            ps = con.prepareStatement("select * from buses");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Bus bus = new Bus(rs.getInt("id_bus"), rs.getString("numero_bus"), rs.getInt("capacidad"));
	                listaBuses.add(bus);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new KrakeDevException("Error al obtener los buses: " + e.getMessage(), e);
	        } finally {
	            ConexionBDD.cerrarConexion(con);
	        }
	        return listaBuses;
	    }

	    public void insertar(Bus bus) throws KrakeDevException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        try {
	            con = ConexionBDD.obtenerConexion();
	            ps = con.prepareStatement("insert into buses (numero_bus, capacidad) values (?, ?)");
	            ps.setString(1, bus.getNumeroBus());
	            ps.setInt(2, bus.getCapacidad());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new KrakeDevException("Error al insertar el bus: " + e.getMessage(), e);
	        } finally {
	            ConexionBDD.cerrarConexion(con);
	        }
	    }
	}

	package com.krakedev.buses.bdd;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import com.krakedev.buses.entidades.Ruta;
	import com.krakedev.buses.excepciones.KrakeDevException;
	import com.krakedev.utils.ConexionBDD;

	public class RutasBDD {
	    public ArrayList<Ruta> obtenerTodas() throws KrakeDevException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Ruta> listaRutas = new ArrayList<>();
	        try {
	            con = ConexionBDD.obtenerConexion();
	            ps = con.prepareStatement("select * from rutas");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Ruta ruta = new Ruta(rs.getInt("id_ruta"), rs.getString("ciudad_origen"), rs.getString("ciudad_destino"), rs.getTime("horario_salida"), rs.getTime("horario_llegada"));
	                listaRutas.add(ruta);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new KrakeDevException("Error al obtener las rutas: " + e.getMessage(), e);
	        } finally {
	            ConexionBDD.cerrarConexion(con);
	        }
	        return listaRutas;
	    }

	    public void insertar(Ruta ruta) throws KrakeDevException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        try {
	            con = ConexionBDD.obtenerConexion();
	            ps = con.prepareStatement("insert into rutas (ciudad_origen, ciudad_destino, horario_salida, horario_llegada) values (?, ?, ?, ?)");
	            ps.setString(1, ruta.getCiudadOrigen());
	            ps.setString(2, ruta.getCiudadDestino());
	            ps.setTime(3, ruta.getHorarioSalida());
	            ps.setTime(4, ruta.getHorarioLlegada());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new KrakeDevException("Error al insertar la ruta: " + e.getMessage(), e);
	        } finally {
	            ConexionBDD.cerrarConexion(con);
	        }
	    }
	}
	//////////
	{
	  "numeroBus": "BUS123",
	  "capacidad": 45
	}

	{
	  "ciudadOrigen": "Quito",
	  "ciudadDestino": "Guayaquil",
	  "horarioSalida": "08:00:00",
	  "horarioLlegada": "14:00:00"
	}

	{
	  "nombre": "Juan Perez",
	  "correoElectronico": "juan.perez@example.com"
	}

	{
	  "idUsuario": 1,
	  "idRuta": 5,
	  "cantidadAsientos": 3
	}

	
	
}
