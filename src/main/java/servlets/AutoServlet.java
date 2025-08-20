package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Auto;
import utils.GestorDatos;

@WebServlet(name = "AutoServlet", urlPatterns = {"/AutoServlet"})
public class AutoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Auto> autos = GestorDatos.getAutos(session);
        // Obtener datos del formulario
        String codigo = request.getParameter("codigo");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String imagen = request.getParameter("imagen");
        String anioStr = request.getParameter("anio");
        String precioStr = request.getParameter("precio");
        // Validaciones
        String error = GestorDatos.validarAuto(autos, codigo, marca, modelo, anioStr, precioStr);
        if (error != null) {
            request.setAttribute("mensaje", error);
            request.getRequestDispatcher("/autos/registrarAuto.jsp").forward(request, response);
            return;
        }
        // Si todo OK, crear y agregar auto
        Auto auto = new Auto(codigo, marca, modelo, imagen, Integer.parseInt(anioStr), Double.parseDouble(precioStr), "Disponible");
        autos.add(auto);
        session.setAttribute("autos", autos);
        request.setAttribute("mensaje", "Auto registrado exitosamente.");
        request.getRequestDispatcher("/autos/registrarAuto.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Auto> autos = GestorDatos.getAutos(session);
        String filtro = request.getParameter("filtro");
        ArrayList<Auto> resultado = new ArrayList<>();
        if (filtro == null || filtro.isEmpty()) {
            resultado = autos;
        } else if (filtro.equals("disponibles")) {
            for (Auto a : autos) if ("Disponible".equals(a.getEstado())) resultado.add(a);
        } else if (filtro.equals("vendidos")) {
            for (Auto a : autos) if ("Vendido".equals(a.getEstado())) resultado.add(a);
        } else if (filtro.equals("codigo")) {
            String codigo = request.getParameter("codigo");
            for (Auto a : autos) if (a.getCodigo().equals(codigo)) resultado.add(a);
        } else if (filtro.equals("marca")) {
            String marca = request.getParameter("marca");
            for (Auto a : autos) if (a.getMarca().equalsIgnoreCase(marca)) resultado.add(a);
        } else if (filtro.equals("modelo")) {
            String modelo = request.getParameter("modelo");
            for (Auto a : autos) if (a.getModelo().equalsIgnoreCase(modelo)) resultado.add(a);
        } else if (filtro.equals("precio")) {
            double min = Double.parseDouble(request.getParameter("min"));
            double max = Double.parseDouble(request.getParameter("max"));
            for (Auto a : autos) if (a.getPrecio() >= min && a.getPrecio() <= max) resultado.add(a);
        }
        request.setAttribute("autos", resultado);
        request.getRequestDispatcher("/autos/listarAutos.jsp").forward(request, response);
    }
}