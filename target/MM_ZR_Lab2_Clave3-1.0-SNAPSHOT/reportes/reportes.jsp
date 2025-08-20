<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Reportes</title>
    </head>
    <body>
        <h2>Reportes</h2>
        <ul>
            <li>Autos disponibles: ${disponibles}</li>
            <li>Autos vendidos: ${vendidos}</li>
            <li>Porcentaje vendidos: ${porcentajeVendidos}%</li>
            <li>
                Auto más caro vendido:
                <c:choose>
                    <c:when test="${not empty masCaro}">
                        ${masCaro.marca} ${masCaro.modelo} ($${masCaro.precio})
                    </c:when>
                    <c:otherwise>
                        N/A
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                Auto más barato vendido:
                <c:choose>
                    <c:when test="${not empty masBarato}">
                        ${masBarato.marca} ${masBarato.modelo} ($${masBarato.precio})
                    </c:when>
                    <c:otherwise>
                        N/A
                    </c:otherwise>
                </c:choose>
            </li>
            <li>Promedio precio autos vendidos: $${promedioVendidos}</li>
            <li>Promedio precio autos disponibles: $${promedioDisponibles}</li>
            <li>Total ingresos por ventas: $${totalIngresos}</li>
        </ul>
        <a href="../index.jsp">Volver al inicio</a>
    </body>
</html>