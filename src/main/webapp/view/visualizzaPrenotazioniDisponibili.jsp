<%@ page import="model.Utente.Utente" %>
<%@ page import="model.Prenotazione.PrenotazioneDisponibile" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 05/02/2022
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> PRENOTAZIONI DISPONIBILI</title>
</head>
<body>
<% Utente user=(Utente) request.getSession().getAttribute("user");
    ArrayList<PrenotazioneDisponibile> elencoPrenotazioni = (ArrayList<PrenotazioneDisponibile>) request.getSession().getAttribute("listaPrenotazioniDisponibili");
for(PrenotazioneDisponibile p: elencoPrenotazioni){ %>
<table>
    <tr>
        <th>CAMPO</th>
        <th>DATA</th>
        <th>ORA START</th>
        <th>ORA END</th>
    </tr>
    <tr>
        <td><%=p.getNomeCampo()%></td>
        <td><%=p.getDate()%></td>
        <td><%=p.getOraStart()%></td>
        <td><%=p.getOraEnd()%></td>
    </tr>
</table>

<%}%>

</body>
</html>
