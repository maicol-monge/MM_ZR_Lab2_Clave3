<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Cliente</title>
</head>
<body>
    <h2>Registrar Cliente</h2>
    <form action="../ClienteServlet" method="post">
        Identificador: <input type="text" name="identificador" required><br>
        Nombre: <input type="text" name="nombre" required><br>
        Edad: <input type="number" name="edad" min="18" required><br>
        <input type="submit" value="Registrar">
    </form>
    <c:if test="${not empty mensaje}">
        <div style="color:red;">${mensaje}</div>
    </c:if>
    <a href="../index.jsp">Volver al inicio</a>
</body>
</html>