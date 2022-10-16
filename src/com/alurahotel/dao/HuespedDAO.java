package com.alurahotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alurahotel.modelo.Huesped;
import com.alurahotel.modelo.Reserva;


public class HuespedDAO {
	final private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huesped huesped, Integer reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(" INSERT INTO huespedes (nombre, apellido, "
					+ "fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES(?, ?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			
			try(statement){
				EjecutarRegistro(huesped, statement, reserva);
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void EjecutarRegistro(Huesped huesped, PreparedStatement statement, Integer reserva) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, reserva);
		
		
		statement.execute();
		final ResultSet resulSet = statement.getGeneratedKeys();
		
		try(resulSet){
			while(resulSet.next()) {
				huesped.setId(resulSet.getInt(1));
			}
		}
	}

	public List<Huesped> listar() {
		List<Huesped> huespedes = new ArrayList<>();
		
		try {
			PreparedStatement statement = con.prepareStatement("select * from huespedes");
			
			try(statement){
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					huespedes.add(new Huesped(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getNString("apellido"),
							resultSet.getDate("fecha_nacimiento"),
							resultSet.getString("nacionalidad"),
							resultSet.getString("telefono"),
							resultSet.getInt("id_reserva")));
				}
				return huespedes;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer id) {
		try {
			PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = " + id);
			
			try(statement){
				statement.execute();
				return statement.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		
	}
	
	//Metodo para filtrar los huespedes por su nombre o apellido
	public List<Huesped> listar(String nombre) {
		List<Huesped> huespedes = new ArrayList<>();
		
		try {
			PreparedStatement statement = con.prepareStatement(" select * from huespedes where "
					+ "apellido like '%"+nombre+"%' or id_reserva like '%"+nombre+"%'");
			
			try(statement){
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					huespedes.add(new Huesped(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getNString("apellido"),
							resultSet.getDate("fecha_nacimiento"),
							resultSet.getString("nacionalidad"),
							resultSet.getString("telefono"),
							resultSet.getInt("id_reserva")));
				}
				return huespedes;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int modificar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
		try {
			PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
					+ "nombre = ?, "
					+ "apellido = ?, "
					+ "fecha_nacimiento = ?, "
					+ "nacionalidad = ?, "
					+ "telefono = ? ");
			try(statement){
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
