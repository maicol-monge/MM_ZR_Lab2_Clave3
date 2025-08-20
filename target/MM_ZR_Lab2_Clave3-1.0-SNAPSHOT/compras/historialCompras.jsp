<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Historial de Compras</title>
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
        table {
            width: 100%;
        }
        th {
            background-color: #2e59a6;
            color: white;
        }
        td, th {
            padding: 10px;
            text-align: center;
        }
        .btn-outline-light {
            border: 1px solid white;
            color: white;
        }
        .btn-outline-light:hover {
            background-color: #1abc9c;
            border-color: #1abc9c;
        }
        .alert-custom {
            background-color: #e74c3c;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="card">
                <h2 class="text-center mb-4">Historial de Compras</h2>

                <c:if test="${not empty mensaje}">
                    <div class="alert alert-custom text-center">${mensaje}</div>
                </c:if>

                <c:if test="${not empty compras}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Cliente</th>
                                    <th>Auto</th>
                                    <th>Fecha</th>
                                    <th>Precio</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="compra" items="${compras}">
                                    <tr>
                                        <td>${compra.cliente.nombre}</td>
                                        <td>${compra.auto.marca} ${compra.auto.modelo}</td>
                                        <td>${compra.fecha}</td>
                                        <td>${compra.precio}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

                <div class="text-center mt-3">
                    <a href="../index.jsp" class="btn btn-outline-light">Volver al Inicio</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
