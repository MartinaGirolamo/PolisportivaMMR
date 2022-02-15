<%@ page import="storage.Utente.Utente" %>
<%@ page import="storage.Utente.UtenteDAO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Martina
  Date: 12/02/2022
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MOSTRA UTENTI</title>
    <style>
        table {
            margin-bottom: 20px;
            margin-top: 100px;
            margin-left: 20px;
            border-collapse: collapse;
            width: 97%;
        }

        th, td {
            text-align: center;
            padding: 8px;
        }

        tr:nth-child(even) {background-color: #8c8888;}
        .subBtn{

            display: flex;
            margin: auto;
            margin-top: 10px;
            border: solid #26272b;
            color:#FFFFFF;
            background-color:#26272b  ;
            text-decoration:none;
            padding: 10px 25px 10px 25px;
            transition: 0.6s ease;
            cursor: pointer;
        }

        .subBtn:hover{
            background-color: grey;

        }
    </style>

    <%String context = request.getContextPath();
        Utente user=(Utente) request.getSession().getAttribute("user");
        UtenteDAO utenteDAO = new UtenteDAO();
        ArrayList<Utente> listaUtenti = utenteDAO.selectAllUtenti();
    %>
</head>
<body>
<% if(user==null||!user.isIs_Admin()|| user.getEmail()==null  ){
    RequestDispatcher requestDispatcher= request.getRequestDispatcher("Error500.jsp");
    requestDispatcher.forward(request, response);}%>

<jsp:include page="/view/headerLog.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<h2><p>UTENTI</p></h2>
<table>
    <tr>
        <th>EMAIL </th>
        <th>NOME</th>
        <th>COGNOME</th>
        <th>ADMIN</th>
        <th>ELIMINA</th>

    </tr>

    <%
        for (Utente u: listaUtenti) {
    %>
    <tr>
        <td><%=u.getEmail()%></td>
        <td><%=u.getNome()%></td>
        <td><%=u.getCognome()%></td>
        <td><%=u.isIs_Admin()%></td>
        <td><form action="../ServletEliminaUtente" method="post">
            <input type="hidden" name="email" value=<%=u.getEmail()%> ><button type="submit" class="subBtn">ELIMINA</button></form></td>

    </tr>
    <% }%>
</table>

<div >
    <p>VUOI INSERIRE UN NUOVO AMMINISTRATORE?  <a href="register.jsp"> CREA!</a></p>
</div>

<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

</body>
</html>
