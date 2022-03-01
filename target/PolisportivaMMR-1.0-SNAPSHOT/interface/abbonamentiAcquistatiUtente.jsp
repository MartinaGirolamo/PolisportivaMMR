<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Acquisto.AcquistoDAO" %>
<%@ page import="storage.Abbonamento.AbbonamentoDAO" %>
<%@ page import="storage.Acquisto.Acquisto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.Abbonamento.Abbonamento" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 12/02/2022
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
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
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {background-color: #8c8888;}
    </style>

    <%String context = request.getContextPath();
        Utente user=(Utente) request.getSession().getAttribute("user");
        AcquistoDAO acquistoDAO = new AcquistoDAO();
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
        ArrayList<Acquisto> elencoAcquisti = acquistoDAO.selectAcquistoByUtente(user.getEmail());
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

<h2>I MIEI ABBONAMENTI</h2>
<table>
    <tr>
        <th>TIPOLOGIA</th>
        <th>DATA ACQUISTO</th>
        <th>NUMERO MESI</th>
        <th>TARIFFA TOTALE</th>
        <th>ELIMINA</th>
    </tr>
    <%for(int i = 0; i<elencoAcquisti.size();i++){
        Acquisto acq = elencoAcquisti.get(i);
        Abbonamento a = abbonamentoDAO.selectAbbonamentoByCodice(acq.getCodiceAbb());%>

    <tr>
        <td><%=a.getTipologia()%></td>
        <td><%=acq.getDataAcquisto()%></td>
        <td><%=acq.getnMesi()%></td>
        <td><%=a.getTariffa()* acq.getnMesi()%></td>
        <td><form action="../ServletEliminaAcquisto" method="post">
            <input type="hidden" name="tipologia" value=<%=a.getTipologia()%> ><button type="submit" class="subBtn">ELIMINA</button></form></td>
    </tr>
    <%}%>
</table>


<jsp:include page="/interface/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

</body>
</html>

