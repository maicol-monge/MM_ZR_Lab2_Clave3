<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historial de Compras</title>
</head>
<body>
    <h2>Historial de Compras</h2>
    <c:if test="${not empty mensaje}">
        <div>${mensaje}</div>
    </c:if>
    <c:if test="${not empty compras}">
        <table border="1">
            <tr>
                <th>Cliente</th><th>Auto</th><th>Fecha</th><th>Precio</th>
            </tr>
            <c:forEach var="compra" items="${compras}">
                <tr>
                    <td>${compra.cliente.nombre}</td>
                    <td>${compra.auto.marca} ${compra.auto.modelo}</td>
                    <td>${compra.fecha}</td>
                    <td>${compra.precio}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <a href="../index.jsp">Volver al inicio</a>
</body>
</html>