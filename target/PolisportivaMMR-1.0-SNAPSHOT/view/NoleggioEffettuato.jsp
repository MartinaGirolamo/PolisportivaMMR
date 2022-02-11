<%@ page import="model.Utente.Utente" %>
<%@ page import="model.Prenotazione.Prenotazione" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 07/02/2022
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> NOLEGGIO EFFETTUATO</title>

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
<%  Utente user=(Utente) request.getSession().getAttribute("user");
    if(user==null || user.getEmail()==null){%>
<jsp:include page="/view/headerNotLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}

else if(!user.isIs_Admin()){%>
<jsp:include page="/view/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}%>

<div class="container">
    <h2> SUCCESSO!</h2>
    <p> NOLEGGIO EFFETTUATO CON SUCCESSO!</p>
    <p> Per visualizzare i dettagli, controllare sul profilo personale</p>
</div>


</div>

<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
