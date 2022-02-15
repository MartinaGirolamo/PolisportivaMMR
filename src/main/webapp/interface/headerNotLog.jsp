<%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 01/02/22
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/css/header.css"%>
        * {box-sizing: border-box;}

        body {
            margin: 0;

        }
        .descrizione{
            text-align: center;
        }

        .mySlides {display: none}
        img {vertical-align: middle;}

        /* Slideshow container */
        .slideshow-container {
            max-width: 1000px;
            position: relative;
            margin: auto;
        }

        /* Next & previous buttons */
        .prev, .next {
            cursor: pointer;
            position: absolute;
            top: 50%;
            width: auto;
            padding: 16px;
            margin-top: -22px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
            user-select: none;
        }

        /* Position the "next button" to the right */
        .next {
            right: 0;
            border-radius: 3px 0 0 3px;
        }

        /* On hover, add a black background color with a little bit see-through */
        .prev:hover, .next:hover {
            background-color: rgba(0,0,0,0.8);
        }

        /* Caption text */
        .text {
            color: #f2f2f2;
            font-size: 15px;
            padding: 8px 12px;
            position: absolute;
            bottom: 8px;
            width: 100%;
            text-align: center;
        }





        /* On smaller screens, decrease text size */
        @media only screen and (max-width: 300px) {
            .prev, .next,.text {font-size: 11px}
        }
        <%String contex=request.getContextPath();%>
    </style>
</head>
<body>
<div class="header">

    <a href="<%=contex%>" class="logo"><img src="<%=contex%>/immagini/Logo.png" width="100px" height="100px"></a>
    <div class="header-center">
        <a class="notLast" href="<%=contex%>/view/abbonamenti.jsp">Abbonamenti</a>
        <a class="notLast" href="<%=contex%>/view/prenotazione.jsp">Prenota</a>
        <a href="<%=contex%>/view/campi.jsp">Campi</a>
    </div>

    <div class="header-right">
        <a class="button" href="<%=contex%>/view/login.jsp"> LOGIN</a>
    </div>
</div>
</body>
</html>
