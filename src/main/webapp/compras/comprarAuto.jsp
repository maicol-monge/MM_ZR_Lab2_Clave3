<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.Auto" %>
<%@ page import="modelo.Cliente" %>
<%
    ArrayList<Auto> autos = (ArrayList<Auto>) session.getAttribute("autos");
    ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Comprar Auto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #1abc9c, #388cc8);
            font-family: 'Segoe UI', sans-serif;
            min-height: 100vh;
            padding-top: 50px;
        }
        .card-auto {
            cursor: pointer;
            transition: box-shadow 0.2s;
        }
        .card-auto.selected, .card-auto:hover {
            box-shadow: 0 0 0 4px #1abc9c;
            border-color: #1abc9c;
        }
        .card-img-top {
            object-fit: cover;
            height: 160px;
        }
        .alert-info {
            background: #eafaf1;
            color: #388cc8;
        }
    </style>
    <script>
        // Guardar clientes en JS para filtrar
        var clientesJS = [
            <% if (clientes != null) {
                for (Cliente c : clientes) { %>
            {id: "<%=c.getIdentificador()%>", nombre: "<%=c.getNombre()%>"},
            <%  }
            } %>
        ];

        function renderClientes(filtro) {
            var select = document.getElementById('selectCliente');
            select.innerHTML = "";
            filtro = filtro.toLowerCase();
            var encontrados = 0;
            clientesJS.forEach(function(c) {
                if (c.id.toLowerCase().includes(filtro) || c.nombre.toLowerCase().includes(filtro)) {
                    var opt = document.createElement('option');
                    opt.value = c.id;
                    opt.textContent = c.nombre + " (" + c.id + ")";
                    select.appendChild(opt);
                    encontrados++;
                }
            });
            select.disabled = encontrados === 0;
        }

        function filtrarClientes() {
            var filtro = document.getElementById('buscadorCliente').value;
            renderClientes(filtro);
            validarCompra();
        }

        function selectAuto(codigo) {
            var cards = document.querySelectorAll('.card-auto');
            cards.forEach(function(card) {
                card.classList.remove('selected');
            });
            var selectedCard = document.getElementById('card-' + codigo);
            if (selectedCard) selectedCard.classList.add('selected');
            document.getElementById('codigoAuto').value = codigo;
            var info = document.getElementById('info-' + codigo).innerHTML;
            document.getElementById('autoSeleccionado').innerHTML = info;
            validarCompra();
        }

        function validarCompra() {
            var clienteSel = document.getElementById('selectCliente');
            var autoSel = document.getElementById('codigoAuto').value;
            var btn = document.getElementById('btnComprar');
            btn.disabled = !(clienteSel && clienteSel.value && autoSel);
        }

        window.onload = function() {
            renderClientes("");
            validarCompra();
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <h2 class="mb-4 text-center">Comprar Auto</h2>
                        <form action="../CompraServlet" method="post">
                            <div class="mb-3">
                                <label class="form-label">Buscar cliente por código o nombre:</label>
                                <input type="text" id="buscadorCliente" class="form-control mb-2" onkeyup="filtrarClientes()" placeholder="Buscar cliente...">
                                <label class="form-label">Cliente:</label>
                                <select name="idCliente" id="selectCliente" class="form-select" required onchange="validarCompra()">
                                    <!-- Opciones generadas por JS -->
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Selecciona un auto:</label>
                                <div class="row row-cols-1 row-cols-md-2 g-3">
                                    <%
                                        boolean hayAutos = false;
                                        if (autos != null) {
                                            for (Auto a : autos) {
                                                if ("Disponible".equals(a.getEstado())) {
                                                    hayAutos = true;
                                    %>
                                    <div class="col">
                                        <div class="card card-auto" id="card-<%=a.getCodigo()%>" onclick="selectAuto('<%=a.getCodigo()%>')">
                                            <img src="<%=a.getImagen()%>" class="card-img-top" alt="Foto auto">
                                            <div class="card-body">
                                                <h5 class="card-title"><%=a.getMarca()%> <%=a.getModelo()%></h5>
                                                <p class="card-text">
                                                    Año: <%=a.getAnio()%><br>
                                                    Precio: $<%=a.getPrecio()%><br>
                                                    Código: <%=a.getCodigo()%>
                                                </p>
                                            </div>
                                        </div>
                                        <div id="info-<%=a.getCodigo()%>" style="display:none;">
                                            <div class="card border-success mt-3">
                                                <img src="<%=a.getImagen()%>" class="card-img-top" alt="Foto auto">
                                                <div class="card-body">
                                                    <h5 class="card-title text-success"><%=a.getMarca()%> <%=a.getModelo()%></h5>
                                                    <p class="card-text">
                                                        Año: <%=a.getAnio()%><br>
                                                        Precio: $<%=a.getPrecio()%><br>
                                                        Código: <%=a.getCodigo()%>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%
                                                }
                                            }
                                        }
                                    %>
                                </div>
                                <input type="hidden" name="codigoAuto" id="codigoAuto" required>
                            </div>
                            <div class="mb-3" id="autoSeleccionado"></div>
                            <%
                                boolean hayClientes = (clientes != null && !clientes.isEmpty());
                                boolean puedeComprar = hayClientes && hayAutos;
                            %>
                            <button type="submit" class="btn btn-primary mt-2" id="btnComprar" <%= puedeComprar ? "" : "disabled" %>>Comprar</button>
                            <c:if test="${not empty mensaje}">
                                <div class="alert alert-info mt-3">${mensaje}</div>
                            </c:if>
                            <%
                                if (!hayClientes) {
                            %>
                                <div class="alert alert-warning mt-3">No hay clientes registrados. Registra un cliente antes de comprar.</div>
                            <%
                                }
                                if (!hayAutos) {
                            %>
                                <div class="alert alert-warning mt-3">No hay autos disponibles para comprar.</div>
                            <%
                                }
                            %>
                            <div class="text-center mt-3">
                                <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline-light btn-primary">Volver al Inicio</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
