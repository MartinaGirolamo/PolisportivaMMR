<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Prenotazione.Prenotazione" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 07/02/2022
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> UTENTE ELIMINATO</title>

    <style>
    .container{
    background-color: #8c8888;
    border: 3px solid #570900;
    padding: 3px;
    margin: 25px auto;
    width: 250px;
    }
    </style>
</head>
<body>
<%
    Utente user=(Utente) request.getSession().getAttribute("user");
    if(user==null || user.getEmail()==null){%>
<jsp:include page="/interface/headerNotLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}

else {%>
<jsp:include page="/interface/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}%>



    <h2> UTENTE ELIMINATO  </h2>
    <p> TORNA AL PROFILO <br> <a href="interface/profiloAdmin.jsp"> CLICCA QUI</a></p>



<jsp:include page="/interface/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
