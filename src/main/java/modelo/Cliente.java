/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author monza
 */
public class Cliente {
	private static int contador = 1;
	private String identificador;
	private String nombre;
	private int edad;

	public Cliente(String nombre, int edad) {
		this.identificador = "C" + contador++;
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getIdentificador() { return identificador; }
	public void setIdentificador(String identificador) { this.identificador = identificador; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public int getEdad() { return edad; }
	public void setEdad(int edad) { this.edad = edad; }

	@Override
	public String toString() {
		return "Cliente{" +
				"identificador='" + identificador + '\'' +
				", nombre='" + nombre + '\'' +
				", edad=" + edad +
				'}';
	}
}
