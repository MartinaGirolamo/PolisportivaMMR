<%@ page import="model.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 25/01/22
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Prenota</title>

    <style>
        select{
            padding:5px;
            font-size: 22px;
            width: 180px;

        }
        #data{

            height: 39px;
            width: 180px;
        }
        .selector{
            flex-direction: column;

            margin-bottom: 10px;
            font-size: x-large;
        }
        .elenco{
            align-items: baseline;
        }
        .selector,.elenco{
            display: flex;
            flex: 50%;
            justify-content: center;
            flex-direction: column;
        }
        .container{
            display: flex;
            justify-content: center;
            align-items: center;
            height: 300px;
            border: 2px solid;
            border-radius: 50px;
            width: 80%;
            margin:auto;
        }
        #title{
            text-align: center;
            font-size: 25px;
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
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
        #campo,#dataDiv,#oraDiv,#numOre{
            display: flex;
            flex: 50%;
            justify-content: center;
            align-items: center;
            margin-top: 10px;
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
<form action="../ServletMostraPrenotazione" method="post">


<label id="title">PRENOTA IL CAMPO</label>
<div class="container">
<div class="selector">
    <div id="campo">
    <label>Seleziona Campo:  </label>

    </div>

    <div id="dataDiv">
        <label>Seleziona Data:  </label>

    </div>

    <div id="oraDiv">
        <label>Seleziona Ora:  </label>

    </div>
    <div id="numOre">
        <label>Quante ore:  </label>

    </div>
</div>
<div class="elenco">
    <input id="campoScelto">
      <select class="campo"  for="campoScelto" required>
        <option>Calcio</option>
        <option>Pallavolo</option>
        <option>Tennis</option>
    </select>
         <input type="date" id="dataScelta">

        <input id="oraStartScelta">
        <select class="oraStart" for="oraStartScelta" required>
            <option>09:00</option>
            <option>10:00</option>
            <option>11:00</option>
            <option>12:00</option>
            <option>13:00</option>
            <option>14:00</option>
            <option>15:00</option>
            <option>16:00</option>
            <option>17:00</option>
            <option>18:00</option>
            <option>19:00</option>
            <option>20:00</option>
        </select>

         <input id="numOreScelte">
         <select class="oraStart" for="numOreScelte" required>
            <option>1</option>
            <option>2</option>
            <option>3</option>

        </select>
</div>
</div>

<input type="submit" onclick="" id="subBtn">
</form>
<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
