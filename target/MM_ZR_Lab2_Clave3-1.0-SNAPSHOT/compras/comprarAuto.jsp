<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Comprar Auto</title>
</head>
<body>
    <h2>Comprar Auto</h2>
    <form action="../CompraServlet" method="post">
        Identificador Cliente: <input type="text" name="idCliente" required><br>
        Código Auto: <input type="text" name="codigoAuto" required><br>
        <input type="submit" value="Comprar">
    </form>
    <c:if test="${not empty mensaje}">
        <div style="color:red;">${mensaje}</div>
    </c:if>
    <a href="../index.jsp">Volver al inicio</a>
</body>
</html>