<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Prenotazione.Prenotazione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="storage.Utente.UtenteDAO" %><%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 25/01/22
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Prenota</title>

    <style>
        select{
            padding:5px;
            font-size: 22px;
            width: 180px;

        }
        #data{

            height: 39px;
            width: 180px;
        }
        .selector{
            flex-direction: column;

            margin-bottom: 10px;
            font-size: x-large;
        }
        .elenco{
            align-items: baseline;
        }
        .selector,.elenco{
            display: flex;
            flex: 50%;
            justify-content: center;
            flex-direction: column;
        }
        .container{
            display: flex;
            justify-content: center;
            align-items: center;
            height: 300px;
            border: 2px solid;
            border-radius: 50px;
            width: 80%;
            margin:auto;
        }
        #title{
            margin-top: 20px;
            text-align: center;
            font-size: 25px;
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        #subBtn{
            font-size: 25px;
            display: flex;
            margin: auto;
            margin-top: 20px;
            border: solid #26272b;
            color:#FFFFFF;
            background-color:#26272b  ;
            text-decoration:none;
            padding: 10px 25px 10px 25px;
            transition: 0.6s ease;
            cursor: pointer;
        }

        #subBtn:hover{
            background-color: grey;

        }
        #campo,#dataDiv,#oraDiv,#numOre{
            display: flex;
            flex: 50%;
            justify-content: center;
            align-items: center;
            margin-top: 10px;
        }
        input[type=date]{
            width: 180px;
            height: 39px;
        }

        table {
            margin-bottom: 20px;
            margin-top: 100px;
            margin-left: 20px;
            border-collapse: collapse;
            width: 97%;
        }

        th, td {
            text-align: center;
            padding: 8px;
        }
        tr:nth-child(even) {background-color: #8c8888;}


    </style>
    <%String context = request.getContextPath();
        Utente user=(Utente) request.getSession().getAttribute("user");
        UtenteDAO ud = new UtenteDAO();
        PrenotazioneDAO pd = new PrenotazioneDAO();
        ArrayList<Prenotazione> elencoPrenotazioni = pd.selectPrenotazioniCampo("Calcio");
    %>
</head>
<body>
<% if(user==null|| user.getEmail()==null){%>
<jsp:include page="/interface/headerNotLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}

else {%>
<jsp:include page="/interface/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}%>
<form action="../ServletBloccaData" method="post">


<label id="title">BLOCCA DATA PER TUTTI I CAMPI</label>
<div class="container">
<div class="selector">

    <div id="dataDiv">
        <label>Seleziona Data:  </label>

    </div>

</div>
<div class="elenco">
    <input type="date" name="dataScelta" required>

</div>
</div>

<input type="submit" onclick="" id="subBtn">
</form>

<h2><p>DATE BLOCCATE</p></h2>
<table>
    <tr>
        <th>UTENTE </th>
        <th>DATA</th>

    </tr>

    <%
        for (Prenotazione p: elencoPrenotazioni) {

            if(ud.isAdmin(p.getEmail())){%>
    <tr>
        <td><%=p.getEmail()%></td>
        <td><%=p.getDateP()%></td>

    </tr>
    <% }}%>
</table>
<jsp:include page="/interface/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
