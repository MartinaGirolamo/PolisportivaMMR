<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="storage.Noleggio.NoleggioDAO" %>
<%@ page import="storage.Attrezzatura.AttrezzaturaDAO" %>
<%@ page import="storage.Noleggio.Noleggio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.Prenotazione.Prenotazione" %>
<%@ page import="storage.Attrezzatura.Attrezzatura" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 12/02/2022
  Time: 18:33
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
        PrenotazioneDAO pd = new PrenotazioneDAO();
        NoleggioDAO nd = new NoleggioDAO();
        AttrezzaturaDAO ad = new AttrezzaturaDAO();
        ArrayList<Prenotazione> elencoPrenotazioni = pd.selectPrenotazioneByUtente(user.getEmail());

    %>

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

<h2>NOLEGGI </h2>
<table>
    <tr>
        <th>CODICE PRENOTAZIONE</th>
        <th>NOME ATTREZZATURA</th>
        <th>QUANTITA</th>
        <th>TARIFFA TOTALE</th>
    </tr>
    <%for (int i = 0; i<elencoPrenotazioni.size();i++){
        Prenotazione p = elencoPrenotazioni.get(i);
        Noleggio n =  nd.selectNoleggioByPrenotazione(p.getCodice());
        if(n!=null && n.getCodiceAttr()!=0){
            Attrezzatura attrezzatura = ad.getAttrezzaturaByCodice(n.getCodiceAttr());%>
    <tr>
        <td><%=p.getCodice()%></td>
        <td><%=attrezzatura.getNome()%></td>
        <td><%=n.getQta()%></td>
        <td><%=n.getQta()* attrezzatura.getTariffa()%></td>
    </tr>
    <%}}%>
</table>
<%}%>




<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

</body>
</html>