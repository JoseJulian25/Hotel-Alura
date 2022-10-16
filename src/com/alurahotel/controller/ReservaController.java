package com.alurahotel.controller;

import java.sql.Date;
import java.util.List;

import com.alurahotel.dao.ReservaDAO;
import com.alurahotel.factory.ConnectionFactory;
import com.alurahotel.modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDao;
	
	public ReservaController() {
		this.reservaDao = new ReservaDAO(new ConnectionFactory().RecuperaConexion());
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDao.guardar(reserva);
	}

	public List<Reserva> listar() {
		return reservaDao.listar();
	}
	
	public List<Reserva> listar(String id) {
		return reservaDao.listar(id);
	}
	
	public int eliminar(Integer id) {
		return this.reservaDao.eliminar(id);
	}
	
	public int modificar(Date fechaEntrada, Date fechaSalida, String valor, String formaPago, Integer id) {
		return reservaDao.modificar(fechaEntrada, fechaSalida, valor, formaPago, id);
	}
}
