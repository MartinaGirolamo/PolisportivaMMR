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
<%@ page import="model.Attrezzatura.AttrezzaturaDAO" %>
<%@ page import="model.Utente.UtenteDAO" %>
<%@ page import="com.mysql.cj.x.protobuf.MysqlxDatatypes" %><%--
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
            margin-top: 10px;
        }

        .subBtn:hover{
            background-color: grey;

        }
        .element4{
            display: flex;
            justify-content: center;

        }
        .element4 *{
            margin: 10px;
            padding: 5px 10px 5px 10px;
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
       AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
       AcquistoDAO ad = new AcquistoDAO();
       AttrezzaturaDAO attrezzaturaDAO= new AttrezzaturaDAO();
       NoleggioDAO noleggioDAO = new NoleggioDAO();
       UtenteDAO utenteDAO= new UtenteDAO();
       ArrayList<Prenotazione> elencoPrenotazioni = pd.selectAllPrenotazioni();
       ArrayList<Utente> elencoUtenti = utenteDAO.selectAllUtenti();
       ArrayList<Acquisto> listAcquisto = ad.selectAllAcquisti(); //acquistiAbbonamenti
       ArrayList<Noleggio> listaNoleggi = noleggioDAO.selectAllNoleggio();

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
<div class="element4">
    <input type="button" onclick="mostraPrenotazioni()" value="Prenotazioni effettuate" class="subBtn">
    <input type="button" onclick="mostraAbbonamenti()" value="Abbonamenti acquistati" class="subBtn">
    <input type="button" onclick="mostraNoleggi()" value="Noleggi effettuati" class="subBtn">
    <input type="button" onclick="mostraUtenti()" value="Utenti" class="subBtn">
</div>

<script>
    function mostraPrenotazioni() {
        var disPrenotazioni = document.getElementById('prenotazioni');
        var disAbbonamenti = document.getElementById('abbonamenti');
        var disNoleggi = document.getElementById('noleggi');
        var disUtenti = document.getElementById('utenti');

        disPrenotazioni.style.visibility = 'visible';
        disAbbonamenti.style.visibility = 'hidden';
        disNoleggi.style.visibility = 'hidden';
        disUtenti.style.visibility = 'hidden';
    }

    function mostraAbbonamenti() {
        var disPrenotazioni = document.getElementById('prenotazioni');
        var disAbbonamenti = document.getElementById('abbonamenti');
        var disNoleggi = document.getElementById('noleggi');
        var disUtenti = document.getElementById('utenti');

        disAbbonamenti.style.visibility ='visible';
        disPrenotazioni.style.display = 'hidden';
        disNoleggi.style.display = 'hidden';
        disUtenti.style.display = 'hidden';
    }

    function mostraNoleggi() {
        var disPrenotazioni = document.getElementById('prenotazioni');
        var disAbbonamenti = document.getElementById('abbonamenti');
        var disNoleggi = document.getElementById('noleggi');
        var disUtenti = document.getElementById('utenti');

        disNoleggi.style.display = 'inline';
        disAbbonamenti.style.display = 'none';
        disPrenotazioni.style.display = 'none';
        disUtenti.style.display = 'none';
    }

    function mostraUtenti() {
        var disPrenotazioni = document.getElementById('prenotazioni');
        var disAbbonamenti = document.getElementById('abbonamenti');
        var disNoleggi = document.getElementById('noleggi');
        var disUtenti = document.getElementById('utenti');

        disUtenti.style.display = 'inline';
        disAbbonamenti.style.display = 'none';
        disNoleggi.style.display = 'none';
        disPrenotazioni.style.display = 'none';
    }
</script>


<div id="prenotazioni" style="visibility: hidden">
    <p>PRENOTAZIONI EFFETTUATE</p>
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
    for (Prenotazione p: elencoPrenotazioni) { %>
        <tr>
            <td><%=p.getEmail()%></td>
            <td><%=p.getCodice()%></td>
            <td><%=p.getNomeCampo()%></td>
            <td><%=p.getDateP()%></td>
            <td><%=p.getTariffaTotale()%></td>
            <td><%=p.getOraStart()%></td>
            <td><%=p.getOraEnd()%></td>
        </tr>
   <% }%>
</div>

<div id="abbonamenti">
<p>ABBONAMENTI ACQUISTATI</p>
</div>

<div id="noleggi"  >
    <p>NOLEGGI EFFETTUATI</p>
</div>

<div id="utenti" >
    <p>UTENTE</p>
</div>


<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
