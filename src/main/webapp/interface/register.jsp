<%@ page import="storage.Utente.Utente" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arsenal, sans-serif;

        }

        form {
            background-color: #ff3725;
            border: 3px solid #f1f1f1;
            margin: 25px auto;
            width: 270px;

        }

        * {
            box-sizing: border-box;
        }


        .container {
            padding: 3px;
            margin: 0 auto;
            width: 250px;

        }


        input[type=text], input[type=password],input[type=email] {    /* Per le caselle di input*/
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 3px solid #504c4c;
            box-sizing: border-box;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #504c4c;
            outline: none;
        }
        input[type=date]{
            width: 100%;
            height: 45px;
            padding-left: 20px;
            border: 3px solid #504c4c;
        }
        .registerbtn {        /*BOTTONE REGISTRA*/
            background-color: #570900;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        .registerbtn:hover {
            opacity: 1;
        }


        a {
            color: dodgerblue;  /*PER I LINK */
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
<form action="../ServletRegister" method="post">
    <div class="container">
        <h1>Registrati</h1>

        <hr>

        <label for="nome"><b>Nome</b></label>
        <input type="text" placeholder="Enter Nome" name="nome" id="nome" required>

        <label for="cognome"><b>Cognome</b></label>
        <input type="text" placeholder="Enter Cognome" name="cognome" id="cognome" required>

        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Enter Email" name="email" id="email" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" id="psw" required>

        <laber for="dateN"><b>Data di nascite </b></laber>
        <input type="date" placeholder="AAAA-MM-GG" name="dateN" id="dateN" required>

        <hr>

        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>SEI GIA ISCRITTO? <a href="login.jsp">ENTRA</a>.</p>
    </div>
</form>
<jsp:include page="/view/footer.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
</body>
</html>