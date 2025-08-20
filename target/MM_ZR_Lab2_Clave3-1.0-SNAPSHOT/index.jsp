<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema de Compra y Gestión de Autos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #1abc9c, #388cc8);
            min-height: 100vh;
            font-family: 'Segoe UI', sans-serif;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 1.5rem;
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
        .icon {
            font-size: 2rem;
            color: #1abc9c;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
        <div class="container">
            <a class="navbar-brand " href="#">Maicol Monge || Ever Zamora - Laboratorio 2 - Multiplataforma</a>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h1 class="text-center text-white mb-5">Bienvenido al Sistema de Compra y Gestión de Autos</h1>

        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card text-center h-100 shadow">
                    <div class="card-body">
                        <i class="bi bi-car-front icon mb-3"></i>
                        <h5 class="card-title">Registrar Auto</h5>
                        <p class="card-text">Agrega nuevos autos al inventario.</p>
                        <a href="autos/registrarAuto.jsp" class="btn btn-primary">Ir</a>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card text-center h-100 shadow">
                    <div class="card-body">
                        <i class="bi bi-card-list icon mb-3"></i>
                        <h5 class="card-title">Listar Autos</h5>
                        <p class="card-text">Consulta todos los autos registrados.</p>
                        <a href="autos/listarAutos.jsp" class="btn btn-primary">Ir</a>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card text-center h-100 shadow">
                    <div class="card-body">
                        <i class="bi bi-people-fill icon mb-3"></i>
                        <h5 class="card-title">Registrar Cliente</h5>
                        <p class="card-text">Ingresa nuevos clientes al sistema.</p>
                        <a href="clientes/registrarCliente.jsp" class="btn btn-primary">Ir</a>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card text-center h-100 shadow">
                    <div class="card-body">
                        <i class="bi bi-person-lines-fill icon mb-3"></i>
                        <h5 class="card-title">Listar Clientes</h5>
                        <p class="card-text">Consulta todos los clientes registrados.</p>
                        <a href="clientes/listarClientes.jsp" class="btn btn-primary">Ir</a>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card text-center h-100 shadow">
                    <div class="card-body">
                        <i class="bi bi-cash-stack icon mb-3"></i>
                        <h5 class="card-title">Comprar Auto</h5>
                        <p class="card-text">Realiza una compra de auto.</p>
                        <a href="compras/comprarAuto.jsp" class="btn btn-primary">Ir</a>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card text-center h-100 shadow">
                    <div class="card-body">
                        <i class="bi bi-journal-text icon mb-3"></i>
                        <h5 class="card-title">Historial de Compras</h5>
                        <p class="card-text">Revisa el historial de todas las compras realizadas.</p>
                        <a href="compras/historialCompras.jsp" class="btn btn-primary">Ir</a>
                    </div>
                </div>
            </div>

            <div class="col mb-5">
                <div class="card text-center h-100 shadow">
                    <div class="card-body">
                        <i class="bi bi-bar-chart-line icon mb-3"></i>
                        <h5 class="card-title">Reportes</h5>
                        <p class="card-text">Genera reportes detallados del sistema.</p>
                        <a href="reportes/reportes.jsp" class="btn btn-primary">Ir</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
