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

        .prenotabtn {        /*BOTTONE REGISTRA*/
            background-color: #570900;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        .prenotabtn:hover {
            opacity: 1;
        }


    </style>
</head>
<body>
<%
    Utente user=(Utente) request.getSession().getAttribute("user");
    ArrayList<PrenotazioneDisponibile> elencoPrenotazioni = (ArrayList<PrenotazioneDisponibile>) request.getSession().getAttribute("listaPrenotazioniDisponibili");%>

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



<form action="ServletPrenota" method="get">
    <div style="margin: auto">
<table>
    <tr>
        <th>CAMPO</th>
        <th>DATA</th>
        <th>ORA START</th>
        <th>ORA END</th>
        <th>SCEGLI</th>
    </tr>
    <%for(int i = 0; i<elencoPrenotazioni.size();i++){
        PrenotazioneDisponibile p = elencoPrenotazioni.get(i);%>

    <tr>
        <td><%=p.getNomeCampo()%></td>
        <td><%=p.getDate()%></td>
        <td><%=p.getOraStart()%></td>
        <td><%=p.getOraEnd()%></td>
        <td><input type="radio" name="indiceArrayScelto" value="<%=i%>" id="<%=i%>" /> <%=i%> </td>
    </tr>
    <%}%>
</table>
    </div>
    <button type="submit" class="prenotabtn" >Prenota</button>
</form>



<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
