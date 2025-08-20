package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Auto;

@WebServlet(name = "AutoServlet", urlPatterns = {"/AutoServlet"})
public class AutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Auto> autos = (List<Auto>) session.getAttribute("autos");
        if (autos == null) {
            autos = new ArrayList<>();
        }

        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String imagen = request.getParameter("imagen"); // <-- URL de imagen
        String anioStr = request.getParameter("anio");
        String precioStr = request.getParameter("precio");

        // Validar año
        int anio;
        try {
            anio = Integer.parseInt(anioStr);
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "El año debe ser numérico.");
            request.getRequestDispatcher("/autos/registrarAuto.jsp").forward(request, response);
            return;
        }

        // Validar precio
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "El precio debe ser numérico.");
            request.getRequestDispatcher("/autos/registrarAuto.jsp").forward(request, response);
            return;
        }

        // Validar imagen (URL)
        if (imagen == null || imagen.trim().isEmpty()) {
            request.setAttribute("mensaje", "Debes ingresar la URL de la imagen.");
            request.getRequestDispatcher("/autos/registrarAuto.jsp").forward(request, response);
            return;
        }

        // Crear objeto Auto
        Auto auto = new Auto(marca, modelo, imagen, anio, precio, "Disponible");
        autos.add(auto);

        session.setAttribute("autos", autos);

        request.setAttribute("mensaje", "Auto registrado con éxito.");
        request.getRequestDispatcher("/autos/listarAutos.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ArrayList<Auto> autos = (ArrayList<Auto>) session.getAttribute("autos");
        if (autos == null) {
            autos = new ArrayList<>();
            session.setAttribute("autos", autos);
        }

        // Obtener parámetros de filtro
        String codigo = request.getParameter("codigo");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String filtro = request.getParameter("filtro");
        String minStr = request.getParameter("min");
        String maxStr = request.getParameter("max");

        Double min = null;
        Double max = null;
        try {
            if (minStr != null && !minStr.isEmpty()) min = Double.parseDouble(minStr);
        } catch (NumberFormatException e) { min = null; }
        try {
            if (maxStr != null && !maxStr.isEmpty()) max = Double.parseDouble(maxStr);
        } catch (NumberFormatException e) { max = null; }

        ArrayList<Auto> resultado = new ArrayList<>();

        for (Auto a : autos) {
            boolean coincide = true;

            if (codigo != null && !codigo.trim().isEmpty() && !a.getCodigo().equalsIgnoreCase(codigo.trim())) {
                coincide = false;
            }
            if (marca != null && !marca.trim().isEmpty() && !a.getMarca().toLowerCase().contains(marca.trim().toLowerCase())) {
                coincide = false;
            }
            if (modelo != null && !modelo.trim().isEmpty() && !a.getModelo().toLowerCase().contains(modelo.trim().toLowerCase())) {
                coincide = false;
            }
            if (filtro != null && !filtro.isEmpty()) {
                if (filtro.equals("disponibles") && !"Disponible".equals(a.getEstado())) coincide = false;
                if (filtro.equals("vendidos") && !"Vendido".equals(a.getEstado())) coincide = false;
            }
            if (min != null && a.getPrecio() < min) {
                coincide = false;
            }
            if (max != null && a.getPrecio() > max) {
                coincide = false;
            }

            if (coincide) resultado.add(a);
        }

        request.setAttribute("autos", resultado);
        request.getRequestDispatcher("/autos/listarAutos.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para registrar autos con imagen por URL";
    }
}
