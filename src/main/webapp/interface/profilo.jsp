<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.Prenotazione.Prenotazione" %>
<%@ page import="storage.Prenotazione.PrenotazioneDisponibile" %>
<%@ page import="storage.Acquisto.AcquistoDAO" %>
<%@ page import="storage.Acquisto.Acquisto" %>
<%@ page import="storage.Abbonamento.AbbonamentoDAO" %>
<%@ page import="storage.Abbonamento.Abbonamento" %>
<%@ page import="storage.Noleggio.Noleggio" %>
<%@ page import="storage.Noleggio.NoleggioDAO" %>
<%@ page import="storage.Attrezzatura.Attrezzatura" %>
<%@ page import="storage.Attrezzatura.AttrezzaturaDAO" %><%--
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
            align-items: center;
            margin: auto;
        }
        .element3{
            display: flex;
            flex-direction: column;
            padding: 20px;
            width: 50%;
        }

        .element4{
            display: flex;
            justify-content: center;
        }

        .element4 *{
            margin: 10px;
            padding: 5px 10px 5px 10px;
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
       Utente user=(Utente) request.getSession().getAttribute("user");
       String context = request.getContextPath();
   %>
    </style>
</head>
<body>

<% if(user==null){%>
<jsp:include page="/interface/headerNotLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}

else if(!user.isIs_Admin()){%>
<jsp:include page="/interface/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}%>

    <div class="container">

        <div class="element1">
            <form action="../ServletCambiaPassword" method="post">
            <h2>IMPOSTAZIONI</h2>
            <div class="element3">
                <label>Modifica password</label>
                <input type="password" name="passwordAttuale" placeholder="Inserisci password attuale" required>
                <input type="password" name="nuovaPassword" placeholder="Inserisci nuova password" required>
                <input type="password" name="verificaNuovaPassword" placeholder="Verifica nuova password" required>
                <input type="submit" class="subBtn">
            </div>
            </form>
        </div>



        <div class="element2">
            <h2><label>Ciao <%=user.getNome()%> !</label></h2>
            <p>I tuoi Dati:</p>
            <label>Nome: <%=user.getNome()%> </label>
            <label>Cognome <%=user.getCognome()%> </label>
            <label>Email: <%=user.getEmail()%> </label>
            <form action="../ServletLogout" method="get">
                    <button type="submit" class="subBtn" onclick="">Logout</button>

            </form>


        </div>
    </div>


<div class="element4">
    <a class="subBtn" href="<%=context%>/interface/prenotazioniUtente.jsp">MOSTRA PRENOTAZIONI EFFETTUATE</a>
    <a class="subBtn" href="<%=context%>/interface/noleggiEffettuatiUtente.jsp">MOSTRA NOLEGGI EFFETTUATI</a>
    <a class="subBtn" href="<%=context%>/interface/abbonamentiAcquistatiUtente.jsp">MOSTRA ACQUISTI EFFETTUATI</a>
</div>




<jsp:include page="/interface/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
