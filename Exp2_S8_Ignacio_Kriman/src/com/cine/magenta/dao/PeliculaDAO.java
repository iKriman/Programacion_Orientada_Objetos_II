package com.cine.magenta.dao;

import com.cine.magenta.model.Pelicula;
import com.cine.magenta.utils.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class PeliculaDAO {
    public boolean guardarPelicula(Pelicula pelicula) {
        Connection conexion = null;
        PreparedStatement statement = null;
        boolean resultado = false;

        try {
            conexion = ConexionDB.conectar();

            String sql = "INSERT INTO Cartelera (titulo, director, anio, duracion, genero) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);

            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getDirector());
            statement.setInt(3, pelicula.getAnio());
            statement.setInt(4, pelicula.getDuracion());
            statement.setString(5, pelicula.getGenero());


            int filas = statement.executeUpdate();
            if (filas > 0) {
                resultado = true;
                JOptionPane.showMessageDialog(null, "Película guardada correctamente");
            }

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar: " + e.getMessage());
            }
        }
        return resultado;
    }

 
    public ArrayList<Pelicula> obtenerPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = ConexionDB.conectar();


            String sql = "SELECT * FROM Cartelera ORDER BY titulo";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(resultSet.getInt("id"));
                pelicula.setTitulo(resultSet.getString("titulo"));
                pelicula.setDirector(resultSet.getString("director"));
                pelicula.setAnio(resultSet.getInt("anio"));
                pelicula.setDuracion(resultSet.getInt("duracion"));
                pelicula.setGenero(resultSet.getString("genero"));
                peliculas.add(pelicula);
            }

        } catch (Exception e) {
            System.out.println("Error al consultar: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar: " + e.getMessage());
            }
        }
        return peliculas;
    }

  
    public Pelicula buscarPorId(int id) {
        Pelicula pelicula = null;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = ConexionDB.conectar();
            String sql = "SELECT * FROM Cartelera WHERE id = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                pelicula = new Pelicula();
                pelicula.setId(resultSet.getInt("id"));
                pelicula.setTitulo(resultSet.getString("titulo"));
                pelicula.setDirector(resultSet.getString("director"));
                pelicula.setAnio(resultSet.getInt("anio"));
                pelicula.setDuracion(resultSet.getInt("duracion"));
                pelicula.setGenero(resultSet.getString("genero"));
            }

        } catch (Exception e) {
            System.out.println("Error al buscar: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar: " + e.getMessage());
            }
        }
        return pelicula;
    }
}