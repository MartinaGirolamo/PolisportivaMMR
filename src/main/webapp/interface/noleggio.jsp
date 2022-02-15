<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Prenotazione.Prenotazione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.Attrezzatura.Attrezzatura" %>
<%@ page import="storage.Abbonamento.AbbonamentoDAO" %>
<%@ page import="storage.Attrezzatura.AttrezzaturaDAO" %>
<%@ page import="storage.Prenotazione.PrenotazioneDAO" %><%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 08/02/22
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Noleggio</title>
    <style>
        .container{
            display: flex;
            flex-direction: column;
            margin:auto;
            border: 4px inset black;
            border-radius: 50px;
            width: 70%;
        }
        .element{
            display: flex;
            padding: 20px;
            font-size: 28px;
            align-items: center;
            margin: auto;
        }
        .element *{
            margin-right:100px ;
        }
        input[type=checkbox]{
           cursor: pointer;
        }
        #subBtn{
            font-size: 25px;
            display: flex;
            margin: auto;
            margin-top: 20px;
            border: solid #26272b;
            color:#FFFFFF;
            background-color:#26272b  ;
            text-decoration:none;
            padding: 10px 25px 10px 25px;
            transition: 0.6s ease;
            cursor: pointer;
        }

        #subBtn:hover{
            background-color: grey;

        }
    </style>
    <%String contex=request.getContextPath();%>
    <%
        Prenotazione prenotazione = (Prenotazione) request.getSession().getAttribute("prenotazioneEffettuata");
        AttrezzaturaDAO ad = new AttrezzaturaDAO();
        ArrayList<Attrezzatura> list = ad.selectAttrezzaturaByTipologia(prenotazione.getNomeCampo());
        Utente user=(Utente) request.getSession().getAttribute("user");


    %>
</head>
<body>

<% if(user==null || user.getEmail()==null){%>
<jsp:include page="/view/headerNotLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}

else if(!user.isIs_Admin()){%>
<jsp:include page="/view/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<%}%>

<form action="../ServletNoleggia" method="post">
    <div class="container">
        <%for (Attrezzatura a: list)
                  {%>
        <div class="element">
            <img src="<%="../"+a.getPath()%>" width="100px" height="100px">
            <label><%=a.getNome()%></label>
            <input type="checkbox" id="<%=a.getNome()%>" name="<%=a.getTipologia()%>" value="<%=a.getNome()%>">
            <label for="quantity<%=a.getNome()%>"> Inserire quantità:</label>
            <input type="number" name="<%=a.getNome()%>" id="quantity<%=a.getNome()%>" min="1" max="<%=a.getQta()%>">
        </div>
        <%}%>
    </div>
    <input type="submit" onclick="" id="subBtn" value="Noleggia">
</form>

<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
