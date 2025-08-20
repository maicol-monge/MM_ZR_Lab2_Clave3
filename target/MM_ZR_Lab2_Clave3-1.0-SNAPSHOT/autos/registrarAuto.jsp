<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Auto</title>
</head>
<body>
    <h2>Registrar Auto</h2>
    <form action="../AutoServlet" method="post">
        Código: <input type="text" name="codigo" required><br>
        Marca: <input type="text" name="marca" required><br>
        Modelo: <input type="text" name="modelo" required><br>
        Imagen (URL): <input type="text" name="imagen" required><br>
        Año: <input type="number" name="anio" min="1980" max="2025" required><br>
        Precio: <input type="number" name="precio" min="1" step="0.01" required><br>
        <input type="submit" value="Registrar">
    </form>
    <c:if test="${not empty mensaje}">
        <div style="color:red;">${mensaje}</div>
    </c:if>
    <a href="../index.jsp">Volver al inicio</a>
</body>
</html>