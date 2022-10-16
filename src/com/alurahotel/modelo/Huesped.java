package com.alurahotel.modelo;

import java.sql.Date;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer idReserva;
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, 
			Integer idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	

	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdReserva() {
		return idReserva;
	}

	public Integer getId() {
		return this.id;
	}
	
}
