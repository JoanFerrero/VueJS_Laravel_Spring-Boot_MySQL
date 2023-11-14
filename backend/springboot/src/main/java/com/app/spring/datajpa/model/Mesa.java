package com.app.spring.datajpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mesa")
public class Mesa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "capacidad")
	private Integer capacidad;

	public Mesa() {

	}

	public Mesa(String nombre, String categoria, Integer capacidad) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.capacidad = capacidad;
	}

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Mesa [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", capacidad=" + capacidad + "]";
	}
}
