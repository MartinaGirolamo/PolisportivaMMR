<%@ page import="model.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 01/02/22
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<style>
    <%@include file="/css/header.css"%>
</style>
    <%
        Utente user=(Utente) request.getSession().getAttribute("user");
        String contex=request.getContextPath();
    %>

</head>
<body>
<div class="header">

    <a href="<%=contex%>" class="logo"><img src="<%=contex%>/immagini/Logo.png" width="100px" height="100px"></a>
    <div class="header-center">
        <a class="notLast" href="<%=contex%>/view/abbonamenti.jsp">Abbonamenti</a>
        <a class="notLast" href="<%=contex%>/view/prenotazione.jsp">Prenota</a>
        <a href="<%=contex%>/view/campi.jsp">Campi</a>
    </div>


    <%if(!user.isIs_Admin()){%>
    <div class="header-right">
        <a class="button" href="<%=contex%>/view/profilo.jsp"> <%=user.getNome()%></a>
    </div>
    <%}else {%>
    <div class="header-right">
        <a class="button" href="<%=contex%>/view/profiloAdmin.jsp"> <%=user.getNome()%></a>
    </div>
    <%}%>
</div>
</body>
</html>
