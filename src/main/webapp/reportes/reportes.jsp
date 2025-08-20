<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reportes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #1abc9c, #388cc8);
            font-family: 'Segoe UI', sans-serif;
            min-height: 100vh;
            padding-top: 50px;
        }
        .card {
            border-radius: 15px;
            padding: 25px;
            background-color: rgba(255, 255, 255, 0.95);
            box-shadow: 0 8px 20px rgba(0,0,0,0.3);
        }
        .report-list li {
            font-size: 1.1rem;
            padding: 8px 0;
        }
        .btn-outline-light {
            border: 1px solid white;
            color: white;
        }
        .btn-outline-light:hover {
            background-color: #1abc9c;
            border-color: #1abc9c;
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card">
                <h2>Reportes</h2>
                <ul class="report-list list-unstyled">
                    <li><strong>Autos disponibles:</strong> ${disponibles}</li>
                    <li><strong>Autos vendidos:</strong> ${vendidos}</li>
                    <li><strong>Porcentaje vendidos:</strong> ${porcentajeVendidos}%</li>
                    <li><strong>Auto más caro vendido:</strong>
                        <c:choose>
                            <c:when test="${not empty masCaro}">
                                ${masCaro.marca} ${masCaro.modelo} ($${masCaro.precio})
                            </c:when>
                            <c:otherwise>N/A</c:otherwise>
                        </c:choose>
                    </li>
                    <li><strong>Auto más barato vendido:</strong>
                        <c:choose>
                            <c:when test="${not empty masBarato}">
                                ${masBarato.marca} ${masBarato.modelo} ($${masBarato.precio})
                            </c:when>
                            <c:otherwise>N/A</c:otherwise>
                        </c:choose>
                    </li>
                    <li><strong>Promedio precio autos vendidos:</strong> $${promedioVendidos}</li>
                    <li><strong>Promedio precio autos disponibles:</strong> $${promedioDisponibles}</li>
                    <li><strong>Total ingresos por ventas:</strong> $${totalIngresos}</li>
                </ul>
                <div class="text-center mt-3 ">
                        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline-light btn-primary">Volver al Inicio</a>

                    </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
