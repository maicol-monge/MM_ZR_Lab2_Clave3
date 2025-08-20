/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;
import modelo.Auto;
import modelo.Cliente;
import modelo.Compra;

/**
 *
 * @author monza
 */
	public class GestorDatos {
		// Inicializar listas en sesión
		public static ArrayList<Auto> getAutos(HttpSession session) {
			ArrayList<Auto> autos = (ArrayList<Auto>) session.getAttribute("autos");
			if (autos == null) {
				autos = new ArrayList<>();
				session.setAttribute("autos", autos);
			}
			return autos;
		}

		public static ArrayList<Cliente> getClientes(HttpSession session) {
			ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
			if (clientes == null) {
				clientes = new ArrayList<>();
				session.setAttribute("clientes", clientes);
			}
			return clientes;
		}

		public static ArrayList<Compra> getCompras(HttpSession session) {
			ArrayList<Compra> compras = (ArrayList<Compra>) session.getAttribute("compras");
			if (compras == null) {
				compras = new ArrayList<>();
				session.setAttribute("compras", compras);
			}
			return compras;
		}

		// Validaciones para autos
		public static String validarAuto(ArrayList<Auto> autos, String codigo, String marca, String modelo, String anioStr, String precioStr) {
			if (codigo == null || codigo.trim().isEmpty()) return "El código del auto no puede estar vacío.";
			for (Auto a : autos) if (a.getCodigo().equals(codigo)) return "El código del auto debe ser único.";
			if (autos.size() >= 10) return "No se pueden registrar más de 10 autos.";
			if (marca == null || marca.trim().isEmpty()) return "La marca no puede estar vacía.";
			if (modelo == null || modelo.trim().isEmpty()) return "El modelo no puede estar vacío.";
			int anio;
			try { anio = Integer.parseInt(anioStr); } catch (Exception e) { return "El año debe ser un número entero válido."; }
		int actual = Calendar.getInstance().get(Calendar.YEAR);
			if (anio < 1980 || anio > actual) return "El año debe estar entre 1980 y " + actual + ".";
			double precio;
			try { precio = Double.parseDouble(precioStr); } catch (Exception e) { return "El precio debe ser un número válido."; }
			if (precio <= 0) return "El precio debe ser mayor a cero.";
			return null;
		}

		// Validaciones para clientes
		public static String validarCliente(ArrayList<Cliente> clientes, String identificador, String nombre, String edadStr) {
			if (identificador == null || identificador.trim().isEmpty()) return "El identificador no puede estar vacío.";
			for (Cliente c : clientes) if (c.getIdentificador().equals(identificador)) return "El identificador debe ser único.";
			if (nombre == null || nombre.trim().isEmpty()) return "El nombre no puede estar vacío.";
			if (nombre.length() < 3) return "El nombre debe tener al menos 3 caracteres.";
			int edad;
			try { edad = Integer.parseInt(edadStr); } catch (Exception e) { return "La edad debe ser un número entero válido."; }
			if (edad < 18) return "La edad debe ser mayor o igual a 18 años.";
			return null;
		}

		// Validaciones para compra
		public static String validarCompra(ArrayList<Cliente> clientes, ArrayList<Auto> autos, String idCliente, String codigoAuto) {
			Cliente cliente = null;
			for (Cliente c : clientes) if (c.getIdentificador().equals(idCliente)) cliente = c;
			if (cliente == null) return "El cliente no existe.";
			Auto auto = null;
			for (Auto a : autos) if (a.getCodigo().equals(codigoAuto)) auto = a;
			if (auto == null) return "El auto no existe.";
			if (!"Disponible".equals(auto.getEstado())) return "El auto seleccionado no está disponible.";
			return null;
		}

		// Reportes y utilidades
		public static int contarVendidos(ArrayList<Auto> autos) {
			int count = 0;
			for (Auto a : autos) if ("Vendido".equals(a.getEstado())) count++;
			return count;
		}
		public static int contarDisponibles(ArrayList<Auto> autos) {
			int count = 0;
			for (Auto a : autos) if ("Disponible".equals(a.getEstado())) count++;
			return count;
		}
		public static double promedioPrecio(ArrayList<Auto> autos, String estado) {
			double suma = 0;
			int count = 0;
			for (Auto a : autos) if (estado.equals(a.getEstado())) { suma += a.getPrecio(); count++; }
			return count == 0 ? 0 : suma / count;
		}
		public static Auto autoMasCaro(ArrayList<Auto> autos, String estado) {
			Auto max = null;
			for (Auto a : autos) if (estado.equals(a.getEstado())) { if (max == null || a.getPrecio() > max.getPrecio()) max = a; }
			return max;
		}
		public static Auto autoMasBarato(ArrayList<Auto> autos, String estado) {
			Auto min = null;
			for (Auto a : autos) if (estado.equals(a.getEstado())) { if (min == null || a.getPrecio() < min.getPrecio()) min = a; }
			return min;
		}
		public static double totalIngresos(ArrayList<Compra> compras) {
			double suma = 0;
			for (Compra c : compras) suma += c.getPrecio();
			return suma;
		}
		public static double totalGastadoPorCliente(ArrayList<Compra> compras, String idCliente) {
			double suma = 0;
			for (Compra c : compras) if (c.getCliente().getIdentificador().equals(idCliente)) suma += c.getPrecio();
			return suma;
		}
	}
