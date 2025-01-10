package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.DetallePedido;
import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class CategoriaBDD {

    public void insertar(Categoria categoria) throws KrakeDevException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConexionBDD.obtenerConexion();
            ps = con.prepareStatement(
                "insert into categorias (nombre, categoria_padre) values (?, ?)", 
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, categoria.getNombre());
            if (categoria.getCategoriaPadre() != null) {
                ps.setInt(2, categoria.getCategoriaPadre().getCodigo());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();
            System.out.println("Categoría insertada correctamente.");

        } catch (SQLException e) {
            throw new KrakeDevException("Error al insertar la categoría", e);
        }
    }

   
    public void actualizarCategoria(Categoria categoria) throws KrakeDevException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConexionBDD.obtenerConexion();
            ps = con.prepareStatement(
                "update categorias set nombre = ?, categoria_padre = ? where codigo_cat = ?"
            );

            ps.setString(1, categoria.getNombre());
            if (categoria.getCategoriaPadre() != null) {
                ps.setInt(2, categoria.getCategoriaPadre().getCodigo());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            ps.setInt(3, categoria.getCodigo());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Categoría actualizada correctamente.");
            } else {
                System.out.println("No se encontró la categoría con el código especificado.");
            }

        } catch (SQLException e) {
            throw new KrakeDevException("Error al actualizar la categoría", e);
        } 
    }


    public List<Categoria> recuperarCategorias() throws KrakeDevException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();

        try {
            con = ConexionBDD.obtenerConexion();
            ps = con.prepareStatement("select codigo_cat, nombre, categoria_padre from categorias");
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("codigo_cat"));
                categoria.setNombre(rs.getString("nombre"));

                int codigoPadre = rs.getInt("categoria_padre");
                if (!rs.wasNull()) {
                    Categoria categoriaPadre = new Categoria();
                    categoriaPadre.setCodigo(codigoPadre);
                    categoria.setCategoriaPadre(categoriaPadre);
                }

                categorias.add(categoria);
            }

            System.out.println("Categorías recuperadas correctamente.");
        } catch (SQLException e) {
            throw new KrakeDevException("Error al recuperar las categorías", e);
        } 

        return categorias;
    }
}
