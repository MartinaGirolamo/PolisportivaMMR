<%@ page import="model.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 08/02/22
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo</title>
    <style>
        .container{
            display: flex;
            margin: auto;
            width: 80%;
        }
        .element2,.element1{
            flex: 50%;
            font-size: 22px;
        }
        .element2{
            border:2px inset black;
            border-radius: 50px;
            display: flex;
            flex-direction: column;
            padding: 20px;
            align-items: center;
            margin: auto;
        }
        .element3{
            display: flex;
            flex-direction: column;
            padding: 20px;
            width: 50%;
        }
        .element3 *{
            margin-top: 10px;
            padding: 10px;
        }
        .subBtn{
            font-size: 22px;
            border: solid #26272b;
            color:#FFFFFF;
            background-color:#26272b  ;
            text-decoration:none;
            transition: 0.6s ease;
            cursor: pointer;
        }

        .subBtn:hover{
            background-color: grey;

        }
        <%
       Utente user=(Utente) request.getSession().getAttribute("user");
   %>
    </style>
</head>
<body>

<% if(user==null){%>
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
        <div class="element1">
            <h2>IMPOSTAZIONI</h2>
            <div class="element3">
                <label>Modifica nome utente</label>
                <input type="text" placeholder="Inserisci nuovo nome utente">
                <input type="submit" class="subBtn">
            </div>
            <div class="element3">
                <label>Modifica password</label>
                <input type="text" placeholder="Inserisci password attuale" required>
                <input type="text" placeholder="Inserisci nuova password" required>
                <input type="text" placeholder="Verifica nuova password" required>
                <input type="submit" class="subBtn">
            </div>
        </div>
        <div class="element2">
            <h2><label>Ciao *nome*</label></h2>
            <p>I tuoi Dati:</p>
            <label>Nome Utente: </label>
            <label>Nome: </label>
            <label>Cognome </label>
            <label>Email: </label>
        </div>
    </div>
<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
