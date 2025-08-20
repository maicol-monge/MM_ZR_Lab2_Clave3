package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Auto;
import modelo.Cliente;
import modelo.Compra;
import utils.GestorDatos;

@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
        if (clientes == null) {
            clientes = new ArrayList<>();
            session.setAttribute("clientes", clientes);
        }
        ArrayList<Auto> autos = (ArrayList<Auto>) session.getAttribute("autos");
        if (autos == null) {
            autos = new ArrayList<>();
            session.setAttribute("autos", autos);
        }
        ArrayList<Compra> compras = (ArrayList<Compra>) session.getAttribute("compras");
        if (compras == null) {
            compras = new ArrayList<>();
            session.setAttribute("compras", compras);
        }

        String idCliente = request.getParameter("idCliente");
        String codigoAuto = request.getParameter("codigoAuto");

        String error = GestorDatos.validarCompra(clientes, autos, idCliente, codigoAuto);
        if (error != null) {
            request.setAttribute("mensaje", error);
            request.getRequestDispatcher("/compras/comprarAuto.jsp").forward(request, response);
            return;
        }

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

        auto.setEstado("Vendido");
        Compra compra = new Compra(cliente, auto, new Date(), auto.getPrecio());
        compras.add(compra);
        session.setAttribute("autos", autos);
        session.setAttribute("compras", compras);
        // Redirigir al index después de registrar
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Compra> compras = (ArrayList<Compra>) session.getAttribute("compras");
        if (compras == null) {
            compras = new ArrayList<>();
            session.setAttribute("compras", compras);
        }

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