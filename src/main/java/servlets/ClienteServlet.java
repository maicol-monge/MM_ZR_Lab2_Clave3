package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import utils.GestorDatos;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = GestorDatos.getClientes(session);
        // Obtener datos del formulario
        String identificador = request.getParameter("identificador");
        String nombre = request.getParameter("nombre");
        String edadStr = request.getParameter("edad");
        // Validaciones
        String error = GestorDatos.validarCliente(clientes, identificador, nombre, edadStr);
        if (error != null) {
            request.setAttribute("mensaje", error);
            request.getRequestDispatcher("/clientes/registrarCliente.jsp").forward(request, response);
            return;
        }
        // Si todo OK, crear y agregar cliente
        Cliente cliente = new Cliente(identificador, nombre, Integer.parseInt(edadStr));
        clientes.add(cliente);
        session.setAttribute("clientes", clientes);
        request.setAttribute("mensaje", "Cliente registrado exitosamente.");
        request.getRequestDispatcher("/clientes/registrarCliente.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = GestorDatos.getClientes(session);
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/clientes/listarClientes.jsp").forward(request, response);
    }
}