<%@ page import="model.Utente.Utente" %>
<%@ page import="model.Prenotazione.PrenotazioneDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prenotazione.Prenotazione" %>
<%@ page import="model.Prenotazione.PrenotazioneDisponibile" %>
<%@ page import="model.Acquisto.AcquistoDAO" %>
<%@ page import="model.Acquisto.Acquisto" %>
<%@ page import="model.Abbonamento.AbbonamentoDAO" %>
<%@ page import="model.Abbonamento.Abbonamento" %>
<%@ page import="model.Noleggio.Noleggio" %>
<%@ page import="model.Noleggio.NoleggioDAO" %>
<%@ page import="model.Attrezzatura.Attrezzatura" %>
<%@ page import="model.Attrezzatura.AttrezzaturaDAO" %>
<%@ page import="model.Utente.UtenteDAO" %>
<%@ page import="com.mysql.cj.x.protobuf.MysqlxDatatypes" %><%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 08/02/22
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo admin</title>
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
            margin-top: 10px;
        }

        .subBtn:hover{
            background-color: grey;

        }
        .element4{
            display: flex;
            justify-content: center;

        }
        .element4 *{
            margin: 10px;
            padding: 5px 10px 5px 10px;
        }
        table {
            margin-bottom: 20px;
            margin-top: 100px;
            margin-left: 20px;
            border-collapse: collapse;
            width: 97%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {background-color: #8c8888;}


        <%
        String context = request.getContextPath();
       Utente user=(Utente) request.getSession().getAttribute("user");
   %>
    </style>
</head>
<body>

<% if(user==null||!user.isIs_Admin()|| user.getEmail()==null  ){
    RequestDispatcher requestDispatcher= request.getRequestDispatcher("Error500.jsp");
    requestDispatcher.forward(request, response);}%>

<jsp:include page="/view/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>


    <div class="container">

        <div class="element1">
            <form action="${pageContext.request.contextPath}/ServletCambiaPassword" method="get">
            <h2>IMPOSTAZIONI</h2>
            <div class="element3">
                <label>Modifica password</label>
                <input type="text" name="passwordAttuale" placeholder="Inserisci password attuale" required>
                <input type="text" name="nuovaPassword" placeholder="Inserisci nuova password" required>
                <input type="text" name="verificaNuovaPassword" placeholder="Verifica nuova password" required>
                <input type="submit" class="subBtn">
            </div>
            </form>
        </div>


        <div class="element2">
            <h2><label>Ciao Admin <%=user.getNome()%> !</label></h2>
            <p>I tuoi Dati:</p>
            <label>Nome: <%=user.getNome()%> </label>
            <label>Cognome <%=user.getCognome()%> </label>
            <label>Email: <%=user.getEmail()%> </label>
            <form action="${pageContext.request.contextPath}/ServletLogout" method="get">
                <button type="submit" class="subBtn" onclick="">Logout</button>
            </form>
        </div>
    </div>


<div class="element4">
    <a class="subBtn" href="<%=context%>/view/prenotazioniEffettuateAdmin.jsp">MOSTRA PRENOTAZIONI EFFETTUATE</a>
    <a class="subBtn" href="<%=context%>/view/noleggiEffettuatiAdmin.jsp">MOSTRA NOLEGGI EFFETTUATI</a>
    <a class="subBtn" href="<%=context%>/view/abbonamentiAcquistatiAdmin.jsp">MOSTRA ACQUISTI EFFETTUATI</a>
    <a class="subBtn" href="<%=context%>/view/mostraUtentiAdmin.jsp">MOSTRA UTENTI</a>
</div>




<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>