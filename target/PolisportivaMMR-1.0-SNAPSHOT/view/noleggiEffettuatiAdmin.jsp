<%@ page import="model.Utente.Utente" %>
<%@ page import="model.Prenotazione.PrenotazioneDAO" %>
<%@ page import="model.Noleggio.Noleggio" %>
<%@ page import="model.Noleggio.NoleggioDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Attrezzatura.AttrezzaturaDAO" %>
<%@ page import="model.Prenotazione.Prenotazione" %>
<%@ page import="model.Attrezzatura.Attrezzatura" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 12/02/2022
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mostra noleggi</title>
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
    </style>

    <%String context = request.getContextPath();
        Utente user=(Utente) request.getSession().getAttribute("user");
        PrenotazioneDAO pd = new PrenotazioneDAO();
        NoleggioDAO nd = new NoleggioDAO();
        AttrezzaturaDAO ad = new AttrezzaturaDAO();
        ArrayList<Noleggio> elencoNoleggi = nd.selectAllNoleggio();

    %>
</head>
<body>
<% if(user==null||!user.isIs_Admin()|| user.getEmail()==null  ){
    RequestDispatcher requestDispatcher= request.getRequestDispatcher("Error500.jsp");
    requestDispatcher.forward(request, response);}%>

<jsp:include page="/view/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<h2><p>NOLEGGI EFFETTUATI PER PRENOTAZIONE</p></h2>
<table>
    <tr>
        <th>UTENTE </th>
        <th>CODICE PRENOTAZIONE</th>
        <th>NOME ATTREZZATURA</th>
        <th>QUANTITA</th>

    </tr>

    <%
        for (Noleggio n: elencoNoleggi) {
            Prenotazione p = pd.selectPrenotazioneByCodice(n.getCodicePren());
            Attrezzatura a = ad.getAttrezzaturaByCodice(n.getCodiceAttr());
    %>
    <tr>
        <td><%=p.getEmail()%></td>
        <td><%=p.getCodice()%></td>
        <td><%=a.getNome()%></td>
        <td><%=n.getQta()%></td>

    </tr>
    <% }%>
</table>

<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
