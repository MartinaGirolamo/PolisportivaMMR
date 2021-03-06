<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Prenotazione.Prenotazione" %>
<%@ page import="storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.Utente.UtenteDAO" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 12/02/2022
  Time: 17:44
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
            text-align: center;
            padding: 8px;
        }
        tr:nth-child(even) {background-color: #8c8888;}
    </style>
    <%String context = request.getContextPath();
        Utente user=(Utente) request.getSession().getAttribute("user");
        UtenteDAO ud = new UtenteDAO();
        PrenotazioneDAO pd = new PrenotazioneDAO();
        ArrayList<Prenotazione> elencoPrenotazioni = pd.selectAllPrenotazioni();
    %>
</head>
<body>
<% if(user==null||!user.isIs_Admin()|| user.getEmail()==null  ){
    RequestDispatcher requestDispatcher= request.getRequestDispatcher("Error500.jsp");
    requestDispatcher.forward(request, response);}%>

<jsp:include page="/interface/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<h2><p>PRENOTAZIONI EFFETTUATE</p></h2>
<table>
    <tr>
        <th>UTENTE </th>
        <th>CODICE PRENOTAZIONE</th>
        <th>CAMPO</th>
        <th>DATA</th>
        <th>TARIFFA</th>
        <th>ORA START</th>
        <th>ORA END</th>
    </tr>

        <%
    for (Prenotazione p: elencoPrenotazioni) {

        if(!ud.isAdmin(p.getEmail())){%>
    <tr>
        <td><%=p.getEmail()%></td>
        <td><%=p.getCodice()%></td>
        <td><%=p.getNomeCampo()%></td>
        <td><%=p.getDateP()%></td>
        <td><%=p.getTariffaTotale()%></td>
        <td><%=p.getOraStart()%></td>
        <td><%=p.getOraEnd()%></td>
    </tr>
        <% }}%>
</table>




    <jsp:include page="/interface/footer.jsp">
        <jsp:param name="title" value=""/>
    </jsp:include>
</body>
</html>
