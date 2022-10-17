package com.alurahotel.controller;

import java.sql.Date;
import java.util.List;

import com.alurahotel.dao.HuespedDAO;
import com.alurahotel.factory.ConnectionFactory;
import com.alurahotel.modelo.Huesped;

public class HuespedController {
	private HuespedDAO huespedDao;
	
	public HuespedController() {
		this.huespedDao = new HuespedDAO(new ConnectionFactory().RecuperaConexion());
	}
	
	public void guardar(Huesped huesped, Integer reserva) {
		this.huespedDao.guardar(huesped, reserva);
	}

	public List<Huesped> listar() {
		return huespedDao.listar();
	}
	
	public List<Huesped> listar(String nombre){
		return huespedDao.listar(nombre);
	}
	
	public int eliminar(Integer id) {
		return this.huespedDao.eliminar(id);
	}
	
	public int modificar(String nombre, String apellido, Date fechaNacimeinto, String nacionalidad, 
			String telefono, Integer idReserva ,Integer id) {
		return huespedDao.modificar(nombre, apellido, fechaNacimeinto, nacionalidad, telefono, idReserva, id);
	}
}
