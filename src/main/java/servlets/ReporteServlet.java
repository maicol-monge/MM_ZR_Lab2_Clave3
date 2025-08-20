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
import modelo.Compra;
import utils.GestorDatos;

@WebServlet(name = "ReporteServlet", urlPatterns = {"/ReporteServlet"})
public class ReporteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Auto> autos = GestorDatos.getAutos(session);
        ArrayList<Compra> compras = GestorDatos.getCompras(session);

        int vendidos = GestorDatos.contarVendidos(autos);
        int disponibles = GestorDatos.contarDisponibles(autos);
        int total = autos.size();
        double porcentajeVendidos = total > 0 ? (vendidos * 100.0) / total : 0;

        Auto masCaro = GestorDatos.autoMasCaro(autos, "Vendido");
        Auto masBarato = GestorDatos.autoMasBarato(autos, "Vendido");
        double promedioVendidos = GestorDatos.promedioPrecio(autos, "Vendido");
        double promedioDisponibles = GestorDatos.promedioPrecio(autos, "Disponible");
        double totalIngresos = GestorDatos.totalIngresos(compras);

        request.setAttribute("vendidos", vendidos);
        request.setAttribute("disponibles", disponibles);
        request.setAttribute("porcentajeVendidos", porcentajeVendidos);
        request.setAttribute("masCaro", masCaro);
        request.setAttribute("masBarato", masBarato);
        request.setAttribute("promedioVendidos", promedioVendidos);
        request.setAttribute("promedioDisponibles", promedioDisponibles);
        request.setAttribute("totalIngresos", totalIngresos);

        request.getRequestDispatcher("/reportes/reportes.jsp").forward(request, response);
    }
}