/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author monza
 */
import java.util.Date;

public class Compra {
	private Cliente cliente;
	private Auto auto;
	private Date fecha;
	private double precio;

	public Compra(Cliente cliente, Auto auto, Date fecha, double precio) {
		this.cliente = cliente;
		this.auto = auto;
		this.fecha = fecha;
		this.precio = precio;
	}

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }
	public Auto getAuto() { return auto; }
	public void setAuto(Auto auto) { this.auto = auto; }
	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha) { this.fecha = fecha; }
	public double getPrecio() { return precio; }
	public void setPrecio(double precio) { this.precio = precio; }

	@Override
	public String toString() {
		return "Compra{" +
				"cliente=" + cliente +
				", auto=" + auto +
				", fecha=" + fecha +
				", precio=" + precio +
				'}';
	}
    
}
