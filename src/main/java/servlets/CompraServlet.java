package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Auto;
import modelo.Cliente;
import modelo.Compra;
import utils.GestorDatos;

@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = GestorDatos.getClientes(session);
        ArrayList<Auto> autos = GestorDatos.getAutos(session);
        ArrayList<Compra> compras = GestorDatos.getCompras(session);

        String idCliente = request.getParameter("idCliente");
        String codigoAuto = request.getParameter("codigoAuto");

        // Validaciones
        String error = GestorDatos.validarCompra(clientes, autos, idCliente, codigoAuto);
        if (error != null) {
            request.setAttribute("mensaje", error);
            request.getRequestDispatcher("/compras/comprarAuto.jsp").forward(request, response);
            return;
        }

        // Buscar cliente y auto
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getIdentificador().equals(idCliente)) {
                cliente = c;
                break;
            }
        }
        Auto auto = null;
        for (Auto a : autos) {
            if (a.getCodigo().equals(codigoAuto)) {
                auto = a;
                break;
            }
        }

        // Procesar compra
        auto.setEstado("Vendido");
        Compra compra = new Compra(cliente, auto, new Date(), auto.getPrecio());
        compras.add(compra);
        session.setAttribute("autos", autos);
        session.setAttribute("compras", compras);

        request.setAttribute("mensaje", "Compra realizada exitosamente.");
        request.getRequestDispatcher("/compras/comprarAuto.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Compra> compras = GestorDatos.getCompras(session);

        String idCliente = request.getParameter("idCliente");
        ArrayList<Compra> resultado = new ArrayList<>();

        if (idCliente != null && !idCliente.isEmpty()) {
            for (Compra c : compras) {
                if (c.getCliente().getIdentificador().equals(idCliente)) {
                    resultado.add(c);
                }
            }
            request.setAttribute("compras", resultado);
            if (resultado.isEmpty()) {
                request.setAttribute("mensaje", "El cliente no tiene autos comprados.");
            }
        } else {
            request.setAttribute("compras", compras);
            if (compras.isEmpty()) {
                request.setAttribute("mensaje", "No se han realizado compras en el sistema.");
            }
        }
        request.getRequestDispatcher("/compras/historialCompras.jsp").forward(request, response);
    }
}