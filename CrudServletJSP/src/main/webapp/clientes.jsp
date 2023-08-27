<%-- 
    Document   : clientes
    Created on : 26/08/2023, 10:00:22 PM
    Author     : job_a
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CLIENTES</title>
    </head>
    <body>
        <h1>Lista de Clientes</h1>
        <ul>
            <c:forEach var="cliente" items="${clientes}">
                
                <li>${cliente.idCliente} ${cliente.nombre} ${cliente.apellido} ${cliente.saldo}</li>
    
            </c:forEach>
        </ul>
    </body>
</html>
