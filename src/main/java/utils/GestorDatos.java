/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package utils;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import modelo.Auto;
import modelo.Cliente;
import modelo.Compra;

public class GestorDatos {
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

    // Validaciones para clientes
    public static String validarCliente(ArrayList<Cliente> clientes, String nombre, String edadStr) {
        if (nombre == null || nombre.trim().isEmpty()) return "El nombre no puede estar vacío.";
        if (nombre.trim().length() < 3) return "El nombre debe tener al menos 3 caracteres.";
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (Exception e) {
            return "La edad debe ser un número entero válido.";
        }
        if (edad < 18) return "La edad debe ser mayor o igual a 18 años.";
        return null;
    }

    // Validaciones para autos
    public static String validarAuto(ArrayList<Auto> autos, String marca, String modelo, String anioStr, String precioStr) {
        if (autos.size() >= 10) return "No se pueden registrar más de 10 autos.";
        if (marca == null || marca.trim().isEmpty()) return "La marca no puede estar vacía.";
        if (modelo == null || modelo.trim().isEmpty()) return "El modelo no puede estar vacío.";
        int anio;
        try {
            anio = Integer.parseInt(anioStr);
        } catch (Exception e) {
            return "El año debe ser un número entero válido.";
        }
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (anio < 1980 || anio > currentYear) return "El año debe estar entre 1980 y " + currentYear + ".";
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (Exception e) {
            return "El precio debe ser un número válido.";
        }
        if (precio <= 0) return "El precio debe ser mayor a cero.";
        return null;
    }

    // Validaciones para compra
    public static String validarCompra(ArrayList<Cliente> clientes, ArrayList<Auto> autos, String idCliente, String codigoAuto) {
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getIdentificador().equals(idCliente)) {
                cliente = c;
                break;
            }
        }
        if (cliente == null) return "El cliente seleccionado no existe.";
        Auto auto = null;
        for (Auto a : autos) {
            if (a.getCodigo().equals(codigoAuto)) {
                auto = a;
                break;
            }
        }
        if (auto == null) return "El auto seleccionado no existe.";
        if (!"Disponible".equals(auto.getEstado())) return "El auto seleccionado ya ha sido vendido.";
        return null;
    }

    // Métodos de reporte (ejemplo)
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

    public static Auto autoMasCaro(ArrayList<Auto> autos, String estado) {
        Auto result = null;
        for (Auto a : autos) {
            if (estado.equals(a.getEstado())) {
                if (result == null || a.getPrecio() > result.getPrecio()) result = a;
            }
        }
        return result;
    }

    public static Auto autoMasBarato(ArrayList<Auto> autos, String estado) {
        Auto result = null;
        for (Auto a : autos) {
            if (estado.equals(a.getEstado())) {
                if (result == null || a.getPrecio() < result.getPrecio()) result = a;
            }
        }
        return result;
    }

    public static double promedioPrecio(ArrayList<Auto> autos, String estado) {
        double suma = 0;
        int count = 0;
        for (Auto a : autos) {
            if (estado.equals(a.getEstado())) {
                suma += a.getPrecio();
                count++;
            }
        }
        return count > 0 ? suma / count : 0;
    }

    public static double totalIngresos(ArrayList<Compra> compras) {
        double suma = 0;
        for (Compra c : compras) suma += c.getPrecio();
        return suma;
    }
}
