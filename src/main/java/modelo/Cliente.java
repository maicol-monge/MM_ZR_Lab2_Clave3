/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author monza
 */
public class Cliente {
	private String identificador;
	private String nombre;
	private int edad;

	public Cliente(String identificador, String nombre, int edad) {
		this.identificador = identificador;
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
