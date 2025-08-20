<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Clientes</title>
</head>
<body>
    <h2>Listado de Clientes</h2>
    <c:if test="${empty clientes}">
        <div>No hay clientes registrados.</div>
    </c:if>
    <c:if test="${not empty clientes}">
        <table border="1">
            <tr>
                <th>Identificador</th><th>Nombre</th><th>Edad</th>
            </tr>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.identificador}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.edad}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <a href="../index.jsp">Volver al inicio</a>
</body>
</html>