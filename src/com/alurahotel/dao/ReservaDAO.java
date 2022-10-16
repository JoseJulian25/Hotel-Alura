package com.alurahotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.alurahotel.modelo.Reserva;
import com.alurahotel.view.ReservasView;

public class ReservaDAO {
	final private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reserva reserva) {
		try {
			final PreparedStatement statement =  con.prepareStatement(" INSERT INTO reservas (fecha_entrada, "
					+ " fecha_salida, valor, forma_pago ) VALUES(?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
			
			try(statement){
				EjecutarRegistro(reserva, statement);
			}
					
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void EjecutarRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setString(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());
		
		statement.execute();
		final ResultSet resulSet = statement.getGeneratedKeys();
		
		try(resulSet){
			while(resulSet.next()) {
				reserva.setId(resulSet.getInt(1));
			}
		}
	}

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("select id, fecha_entrada, fecha_salida, "
					+ "valor, forma_pago from reservas");
			try(statement){
				statement.execute();
				
				ResultSet resulSet = statement.getResultSet();
				
				while(resulSet.next()) {
					reservas.add(new Reserva(
							resulSet.getInt("id"),
							resulSet.getDate("fecha_entrada"),
							resulSet.getDate("fecha_salida"),
							resulSet.getString("valor"),
							resulSet.getString("forma_pago")));
				}
				return reservas;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE ID = " + id);
			try(statement){
				statement.execute();
				return statement.getUpdateCount();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int modificar(Date fechaEntrada, Date fechaSalida, String valor, String formaPago, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE reservas set "
					+ "fecha_entrada = ?, "
					+ "fecha_salida = ?, "
					+ "valor = ?, "
					+ "forma_Pago = ?, "
					+ "where id = ?");
			try(statement){
				statement.setDate(1, fechaEntrada);
				statement.setDate(2, fechaSalida);
				statement.setString(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, id);
			
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> listar(String id) {
		List<Reserva> reservas = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("select * from reservas where id like '%"+id+"%'");
			try(statement){
				statement.execute();
				
				ResultSet resulSet = statement.getResultSet();
				
				while(resulSet.next()) {
					reservas.add(new Reserva(
							resulSet.getInt("id"),
							resulSet.getDate("fecha_entrada"),
							resulSet.getDate("fecha_salida"),
							resulSet.getString("valor"),
							resulSet.getString("forma_pago")));
				}
				return reservas;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
