<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Acquisto.AcquistoDAO" %>
<%@ page import="storage.Abbonamento.AbbonamentoDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.Acquisto.Acquisto" %>
<%@ page import="storage.Abbonamento.Abbonamento" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 12/02/2022
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MOSTRA ABBONAMENTI UTENTI</title>
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
        AcquistoDAO acquistoDAO = new AcquistoDAO();
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
        ArrayList<Acquisto> elencoAcquisti = acquistoDAO.selectAllAcquisti();
    %>

</head>
<body>
<% if(user==null||!user.isIs_Admin()|| user.getEmail()==null  ){
    RequestDispatcher requestDispatcher= request.getRequestDispatcher("Error500.jsp");
    requestDispatcher.forward(request, response);}%>

<jsp:include page="/interface/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<h2><p>ACQUISTI EFFETTUATI DAGLI UTENTI</p></h2>
<table>
    <tr>
        <th>UTENTE </th>
        <th>TIPOLOGIA ABBONAMENTO</th>
        <th>DATA ACQUISTO</th>
        <th>NUMERO MESI</th>
        <th>TARIFFA TOTALE</th>

    </tr>

    <%
        for (Acquisto n: elencoAcquisti) {
            Abbonamento a = abbonamentoDAO.selectAbbonamentoByCodice(n.getCodiceAbb());
    %>
    <tr>
        <td><%=n.getUtente()%></td>
        <td><%=a.getTipologia()%></td>
        <td><%=n.getDataAcquisto()%></td>
        <td><%=n.getnMesi()%></td>
        <td><%=n.getnMesi()*a.getTariffa()%></td>

    </tr>
    <% }%>
</table>

<jsp:include page="/interface/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

</body>
</html>
