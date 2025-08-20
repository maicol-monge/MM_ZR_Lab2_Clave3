package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Cliente;
import utils.GestorDatos;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
        if (clientes == null) {
            clientes = new ArrayList<>();
            session.setAttribute("clientes", clientes);
        }

        String nombre = request.getParameter("nombre");
        String edadStr = request.getParameter("edad");

        String error = GestorDatos.validarCliente(clientes, nombre, edadStr);
        if (error != null) {
            request.setAttribute("mensaje", error);
            request.getRequestDispatcher("/clientes/registrarCliente.jsp").forward(request, response);
            return;
        }

        Cliente cliente = new Cliente(nombre, Integer.parseInt(edadStr));
        clientes.add(cliente);
        session.setAttribute("clientes", clientes);
        // Redirigir al index después de registrar
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        // Si quieres mostrar un mensaje, puedes guardarlo en sesión antes de redirigir
        // session.setAttribute("mensaje", "Cliente registrado exitosamente. Identificador generado: " + cliente.getIdentificador());
        // response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
        if (clientes == null) {
            clientes = new ArrayList<>();
            session.setAttribute("clientes", clientes);
        }
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/clientes/listarClientes.jsp").forward(request, response);
    }
}