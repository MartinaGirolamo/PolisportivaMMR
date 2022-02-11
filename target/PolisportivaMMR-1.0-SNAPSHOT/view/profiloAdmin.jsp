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
<%@ page import="model.Attrezzatura.AttrezzaturaDAO" %><%--
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
            padding: 20px;
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
       PrenotazioneDAO pd = new PrenotazioneDAO();
       ArrayList<Prenotazione> elencoPrenotazioni = pd.selectPrenotazioneByUtente(user.getEmail());
       AcquistoDAO ad = new AcquistoDAO();
       NoleggioDAO noleggioDAO = new NoleggioDAO();
       AttrezzaturaDAO attrezzaturaDAO= new AttrezzaturaDAO();
       ArrayList<Acquisto> listAcquisto = ad.selectAcquistoByUtente(user.getEmail());
       AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
   %>
    </style>
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
<%}%>
<form action="../ServletCambiaPassword" method="post">
    <div class="container">
        <div class="element1">
            <h2>IMPOSTAZIONI</h2>
            <div class="element3">
                <label>Modifica password</label>
                <input type="text" name="passwordAttuale" placeholder="Inserisci password attuale" required>
                <input type="text" name="nuovaPassword" placeholder="Inserisci nuova password" required>
                <input type="text" name="verificaNuovaPassword" placeholder="Verifica nuova password" required>
                <input type="submit" class="subBtn">
            </div>
        </div>


        <div class="element2">
            <h2><label>Ciao Admin <%=user.getNome()%> !</label></h2>
            <p>I tuoi Dati:</p>
            <label>Nome: <%=user.getNome()%> </label>
            <label>Cognome <%=user.getCognome()%> </label>
            <label>Email: <%=user.getEmail()%> </label>
            <form action="../ServletLogout" method="get">
                <button type="submit" class="subBtn" onclick="">Logout</button>

            </form>


        </div>
    </div>

</form>
<div class="element2">

</div>

<h2>TUTTE LE PRENOTAZIONI</h2>
<table>
    <tr>
        <th>CODICE PRENOTAZIONE</th>
        <th>CAMPO</th>
        <th>DATA</th>
        <th>TARIFFA</th>
        <th>ORA START</th>
        <th>ORA END</th>
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
    </tr>
    <%}%>
</table>

<h2>TUTTI I NOLEGGI </h2>
<table>
    <tr>
        <th>CODICE PRENOTAZIONE</th>
        <th>NOME ATTREZZATURA</th>
        <th>QUANTITA</th>
        <th>TARIFFA TOTALE</th>
    </tr>
    <%for (int i = 0; i<elencoPrenotazioni.size();i++){
        Prenotazione p = elencoPrenotazioni.get(i);
        Noleggio n =  noleggioDAO.selectNoleggioByPrenotazione(p.getCodice());
        if(n!=null && n.getCodiceAttr()!=0){
            Attrezzatura attrezzatura = attrezzaturaDAO.getAttrezzaturaByCodice(n.getCodiceAttr());%>
    <tr>
        <td><%=p.getCodice()%></td>
        <td><%=attrezzatura.getNome()%></td>
        <td><%=n.getQta()%></td>
        <td><%=n.getQta()* attrezzatura.getTariffa()%></td>
    </tr>
    <%}}%>
</table>

<h2>TUTTI GLI ABBONAMENTI</h2>
<table>
    <tr>
        <th>TIPOLOGIA</th>
        <th>DATA ACQUISTO</th>
        <th>NUMERO MESI</th>
        <th>TARIFFA TOTALE</th>
    </tr>
    <%for(int i = 0; i<listAcquisto.size();i++){
        Acquisto acq = listAcquisto.get(i);
        Abbonamento a = abbonamentoDAO.selectAbbonamentoByCodice(acq.getCodiceAbb());%>

    <tr>
        <td><%=a.getTipologia()%></td>
        <td><%=acq.getDataAcquisto()%></td>
        <td><%=acq.getnMesi()%></td>
        <td><%=a.getTariffa()* acq.getnMesi()%></td>
    </tr>
    <%}%>
</table>




<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
