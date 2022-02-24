<%@ page import="storage.Prenotazione.Prenotazione" %>
<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 12/02/2022
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MOSTRA PRENOTAZIONI UTENTI</title>
    <style>
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

        .subBtn{

            display: flex;
            margin: auto;
            margin-top: 10px;
            border: solid #26272b;
            color:#FFFFFF;
            background-color:#26272b  ;
            text-decoration:none;
            padding: 10px 25px 10px 25px;
            transition: 0.6s ease;
            cursor: pointer;
        }

        .subBtn:hover{
            background-color: grey;

        }
    </style>
    <%String context = request.getContextPath();
        Utente user=(Utente) request.getSession().getAttribute("user");
        PrenotazioneDAO pd = new PrenotazioneDAO();
        ArrayList<Prenotazione> elencoPrenotazioni = pd.selectPrenotazioneByUtente(user.getEmail());
    %>
</head>
<body>
<% if(user==null){%>
<jsp:include page="/interface/headerNotLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}

else {%>
<jsp:include page="/interface/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}%>

<h2>LE MIE PRENOTAZIONI</h2>
<table>
    <tr>
        <th>CODICE PRENOTAZIONE</th>
        <th>CAMPO</th>
        <th>DATA</th>
        <th>TARIFFA</th>
        <th>ORA START</th>
        <th>ORA END</th>
        <th>ELIMINA</th>
    </tr>
    <%for(int i = 0; i<elencoPrenotazioni.size();i++){
        Prenotazione p = elencoPrenotazioni.get(i);%>

    <tr>
        <td><%=p.getCodice()%></td>
        <td><%=p.getNomeCampo()%></td>
        <td><%=p.getDateP()%></td>
        <td><%=p.getTariffaTotale()%></td>
        <td><%=p.getOraStart()%></td>
        <td><%=p.getOraEnd()%></td>
        <td><form action="../ServletEliminaUtente" method="post">
            <input type="hidden" name="codice" value=<%=p.getCodice()%> ><button type="submit" class="subBtn">ELIMINA</button></form></td>
    </tr>
    <%}%>
</table>




<jsp:include page="/interface/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>