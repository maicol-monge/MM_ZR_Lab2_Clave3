/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author monza
 */
public class Auto {
	private String codigo;
	private String marca;
	private String modelo;
	private String imagen;
	private int anio;
	private double precio;
	private String estado; // "Disponible" o "Vendido"

	public Auto(String codigo, String marca, String modelo, String imagen, int anio, double precio, String estado) {
		this.codigo = codigo;
		this.marca = marca;
		this.modelo = modelo;
		this.imagen = imagen;
		this.anio = anio;
		this.precio = precio;
		this.estado = estado;
	}

	public String getCodigo() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }
	public String getMarca() { return marca; }
	public void setMarca(String marca) { this.marca = marca; }
	public String getModelo() { return modelo; }
	public void setModelo(String modelo) { this.modelo = modelo; }
	public String getImagen() { return imagen; }
	public void setImagen(String imagen) { this.imagen = imagen; }
	public int getAnio() { return anio; }
	public void setAnio(int anio) { this.anio = anio; }
	public double getPrecio() { return precio; }
	public void setPrecio(double precio) { this.precio = precio; }
	public String getEstado() { return estado; }
	public void setEstado(String estado) { this.estado = estado; }

	@Override
	public String toString() {
		return "Auto{" +
				"codigo='" + codigo + '\'' +
				", marca='" + marca + '\'' +
				", modelo='" + modelo + '\'' +
				", imagen='" + imagen + '\'' +
				", anio=" + anio +
				", precio=" + precio +
				", estado='" + estado + '\'' +
				'}';
	}
    
}
