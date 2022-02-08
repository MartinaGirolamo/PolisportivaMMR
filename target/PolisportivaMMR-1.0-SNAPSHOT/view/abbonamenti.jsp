<%@ page import="model.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 04/02/22
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Abbonamenti</title>
    <style>
        .desc label,select{
            font-size: 22px;
            margin-top: 10px;

        }
        .desc{
            border:4px inset;
            padding:10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            border-radius: 50px;
            margin: auto;
            width: 90%;
            margin-top:20px;
        }
        .title{
            font-size: 26px;
        }
        #acqBtn{
            font-size: 25px;
            border: solid #26272b;
            color:#FFFFFF;
            background-color:#26272b  ;
            text-decoration:none;
            padding: 10px 25px 10px 25px;
            transition: 0.6s ease;
            cursor: pointer;
        }
        #acqBtn:hover{
            background-color: grey;

        }
        .center{
            display: flex;
            font-size: 22px;
            align-items: center;
            justify-content: center;
            margin-top: 20px;
        }
        .center *{
            margin: 20px;
        }
    </style>

    <%
        Utente user=(Utente) request.getSession().getAttribute("user");
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
<%}%>
    <div class="desc">
        <label class="title">DESCRIZIONE ABBONAMENTI</label><br>
        <label>Abbonamento Scuola Tennis : Lun/Mer/Ven 16:00/18:00 </label><br>
        <label>Abbonamento Scuola Pallavolo : Lun/Mer/Ven 16:00/18:00 </label><br>
        <label>Abbonamento Scuola Calcio : Lun/Mer/Ven 16:00/18:00 </label><br>
        <label>Abbonamento Palestra : 24H/7GG</label><br>

    </div>

    <div class="desc">
        <label class="title">ACQUISTA ABBONAMENTO</label>
        <div>
            <label>Selezione Num Mesi</label>
            <select class="oraStart" required>
                <option>1</option>
                <option>3</option>
                <option>6</option>
                <option>12</option>
                <option>24</option>
            </select>
        </div>
        <div>
            <label>Scuola Tennis</label>
            <input type="radio" id="tennis" name="abb" value="tennis" checked>
        </div>
        <div>
            <label>Scuola Pallavolo</label>
            <input type="radio" id="pallavolo" name="abb" value="pallavolo">
        </div>
        <div>
            <label>Scuola Calcio</label>
            <input type="radio" id="calcio" name="abb" value="calcio">
        </div>
        <div>
            <label>Palestra</label>
            <input type="radio" id="palestra" name="abb" value="palestra">
        </div>

    </div>
    <div class="center">
        <label>Totale:$tot</label>
        <a href="#" id="acqBtn">Acquista</a>
    </div>
<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
