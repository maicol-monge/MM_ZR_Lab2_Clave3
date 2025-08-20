<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Autos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #1abc9c, #388cc8);
            min-height: 100vh;
            font-family: 'Segoe UI', sans-serif;
            padding-top: 60px;
        }
        .card {
            border-radius: 15px;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.3);
        }
        .card-title {
            font-weight: bold;
            color: #2e59a6;
        }
        .btn-primary {
            background-color: #2e59a6;
            border: none;
        }
        .btn-primary:hover {
            background-color: #1abc9c;
        }
        .estado-disponible {
            color: #1abc9c;
            font-weight: bold;
        }
        .estado-vendido {
            color: #e74c3c;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center text-white mb-5">Listado de Autos</h2>

        <!-- Buscador y filtros -->
        <form class="row g-3 mb-4" method="get" action="${pageContext.request.contextPath}/AutoServlet">
            <div class="col-md-3">
                <input type="text" name="codigo" class="form-control" placeholder="Buscar por código">
            </div>
            <div class="col-md-3">
                <input type="text" name="marca" class="form-control" placeholder="Buscar por marca">
            </div>
            <div class="col-md-3">
                <input type="text" name="modelo" class="form-control" placeholder="Buscar por modelo">
            </div>
            <div class="col-md-3">
                <select name="filtro" class="form-select">
                    <option value="">Todos</option>
                    <option value="disponibles">Disponibles</option>
                    <option value="vendidos">Vendidos</option>
                </select>
            </div>
            <div class="col-md-3">
                <input type="number" name="min" class="form-control" placeholder="Precio mínimo" min="0">
            </div>
            <div class="col-md-3">
                <input type="number" name="max" class="form-control" placeholder="Precio máximo" min="0">
            </div>
            <div class="col-md-3 d-grid">
                <button type="submit" class="btn btn-primary">Buscar / Filtrar</button>
            </div>
            <div class="col-md-3 d-grid">
                <a href="${pageContext.request.contextPath}/AutoServlet" class="btn btn-secondary">Limpiar</a>
            </div>
        </form>

        <c:if test="${empty autos}">
            <c:choose>
                <c:when test="${not empty param.codigo or not empty param.marca or not empty param.modelo or not empty param.filtro or not empty param.min or not empty param.max}">
                    <div class="alert alert-danger text-center">No hay coincidencias con los filtros/búsqueda seleccionados.</div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning text-center">No hay autos registrados.</div>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:if test="${not empty autos}">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach var="auto" items="${autos}">
                    <div class="col">
                        <div class="card h-100 shadow">
                            <div class="d-flex justify-content-center align-items-center" style="height:180px;">
                                <img src="${auto.imagen}" alt="Foto auto" style="max-width:100%; max-height:160px; object-fit:contain;">
                            </div>
                            <div class="card-body text-center">
                                <h5 class="card-title">${auto.marca} ${auto.modelo}</h5>
                                <p class="card-text">
                                    <strong>Código:</strong> ${auto.codigo} <br>
                                    <strong>Año:</strong> ${auto.anio} <br>
                                    <strong>Precio:</strong> $${auto.precio} <br>
                                    <strong>Estado:</strong> 
                                    <span class="${auto.estado == 'Disponible' ? 'estado-disponible' : 'estado-vendido'}">
                                        ${auto.estado}
                                    </span>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <div class="text-center mt-5">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline-light">Volver al Inicio</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
