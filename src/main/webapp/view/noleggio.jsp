<%@ page import="model.Utente.Utente" %><%--
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
    <div class="container">
        <div class="element">
            <img src="../immagini/guanti.jpg" width="100px" height="100px">
            <label>Guantoni</label>
            <input type="checkbox" id="guanti" name="calcio">
        </div>
        <div class="element">
            <img src="..immagini/palloneCalcio.jpg" width="100px" height="100px">
            <label>Pallone</label>
            <input type="checkbox" id="pallone" name="calcio">
        </div>
        <div class="element">
            <img src="/immagini/casacca.jpg" width="100px" height="100px">
            <label>Casacca</label>
            <input type="checkbox" id="casacca" name="calcio">
        </div>
    </div>
    <input type="submit" onclick="" id="subBtn" value="Noleggia">

<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>
