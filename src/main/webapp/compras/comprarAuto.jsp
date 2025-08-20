<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Comprar Auto</title>
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
        .btn-primary {
            background-color: #2e59a6;
            border: none;
        }
        .btn-primary:hover {
            background-color: #1abc9c;
        }
        .form-label {
            font-weight: bold;
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
            <div class="col-md-6">
                <div class="card">
                    <h2 class="text-center mb-4">Comprar Auto</h2>

                    <c:if test="${not empty mensaje}">
                        <div class="alert alert-custom text-center">${mensaje}</div>
                    </c:if>

                    <form action="../CompraServlet" method="post">
                        <div class="mb-3">
                            <label class="form-label">Identificador del Cliente</label>
                            <input type="text" name="idCliente" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Código del Auto</label>
                            <input type="text" name="codigoAuto" class="form-control" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Realizar Compra</button>
                        </div>
                    </form>

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
