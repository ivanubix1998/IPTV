package com.ipartek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "foto")
	private String foto;

	@Column(name = "precio")
	private double precio;

	@Column(name = "iva")
	private int iva;

	public Producto(int id, String nombre, String foto, double precio, int iva) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.foto = foto;
		this.precio = precio;
		this.iva = iva;
	}

	public Producto() {
		super();
		this.id = 0;
		this.nombre = "";
		this.foto = "";
		this.precio = 0.0;
		this.iva = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", foto=" + foto + ", precio=" + precio + ", iva=" + iva
				+ "]";
	}

}
