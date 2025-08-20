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

        <c:if test="${empty autos}">
            <div class="alert alert-warning text-center">No hay autos registrados.</div>
        </c:if>

        <c:if test="${not empty autos}">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach var="auto" items="${autos}">
                    <div class="col">
                        <div class="card h-100 shadow">
                            <img src="${auto.imagen}" class="card-img-top" alt="${auto.marca} ${auto.modelo}" style="height:180px; object-fit:cover;">
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
