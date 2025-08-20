<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Autos</title>
</head>
<body>
    <h2>Listado de Autos</h2>
    <c:if test="${empty autos}">
        <div>No hay autos registrados.</div>
    </c:if>
    <c:if test="${not empty autos}">
        <table border="1">
            <tr>
                <th>Código</th><th>Marca</th><th>Modelo</th><th>Imagen</th><th>Año</th><th>Precio</th><th>Estado</th>
            </tr>
            <c:forEach var="auto" items="${autos}">
                <tr>
                    <td>${auto.codigo}</td>
                    <td>${auto.marca}</td>
                    <td>${auto.modelo}</td>
                    <td><img src="${auto.imagen}" width="80"></td>
                    <td>${auto.anio}</td>
                    <td>${auto.precio}</td>
                    <td>${auto.estado}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <a href="../index.jsp">Volver al inicio</a>
</body>
</html>