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

@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {
    @Override
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
        double totalComprado = 0;

        if (idCliente != null && !idCliente.trim().isEmpty()) {
            for (Compra c : compras) {
                if (c.getCliente().getIdentificador().equalsIgnoreCase(idCliente)) {
                    resultado.add(c);
                    totalComprado += c.getPrecio();
                }
            }
            request.setAttribute("compras", resultado);
            request.setAttribute("totalComprado", totalComprado);
            if (resultado.isEmpty()) {
                request.setAttribute("mensaje", "No hay compras para ese cliente.");
            }
        } else {
            request.setAttribute("compras", compras);
            request.setAttribute("totalComprado", null);
            if (compras.isEmpty()) {
                request.setAttribute("mensaje", "No se han realizado compras en el sistema.");
            }
        }
        request.getRequestDispatcher("/compras/historialCompras.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
        ArrayList<Auto> autos = (ArrayList<Auto>) session.getAttribute("autos");
        ArrayList<Compra> compras = (ArrayList<Compra>) session.getAttribute("compras");
        if (clientes == null) {
            clientes = new ArrayList<>();
            session.setAttribute("clientes", clientes);
        }
        if (autos == null) {
            autos = new ArrayList<>();
            session.setAttribute("autos", autos);
        }
        if (compras == null) {
            compras = new ArrayList<>();
            session.setAttribute("compras", compras);
        }

        String idCliente = request.getParameter("idCliente");
        String codigoAuto = request.getParameter("codigoAuto");

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

        if (cliente == null || auto == null) {
            request.setAttribute("mensaje", "Cliente o auto no válido.");
            request.getRequestDispatcher("/compras/comprarAuto.jsp").forward(request, response);
            return;
        }

        auto.setEstado("Vendido");
        Compra compra = new Compra(cliente, auto, new Date(), auto.getPrecio());
        compras.add(compra);
        session.setAttribute("autos", autos);
        session.setAttribute("compras", compras);

        request.setAttribute("mensaje", "Compra realizada exitosamente.");
        request.getRequestDispatcher("/compras/comprarAuto.jsp").forward(request, response);
    }
}