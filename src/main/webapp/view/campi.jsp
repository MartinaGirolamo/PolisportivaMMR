<%--
  Created by IntelliJ IDEA.
  User: pastore
  Date: 24/01/22
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Campi</title>
    <style>
        .container{
            display: flex;
            text-align: center;
            border: solid 2px;
            border-color: #000;
            height:300px;
            margin:15px 30px 15px 30px;
        }
        .item-left {
            flex: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            font-size: 18px;
        }
        .item-left img{
            text-align: center;
        }
        .item-right{
            flex: 50%;
            text-align: center;
            font-size: 18px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .item-right label{
            text-align: center;
        }
        .btn{
            border: solid #26272b;
            color:#FFFFFF;
            background-color:#26272b  ;
            text-decoration:none;
            padding: 10px 25px 10px 25px;
            transition: 0.6s ease;
            font-size: 22px;
        }
        .btn:hover{
            background-color: grey;

        }
        .list{
            list-style:none;
        }
        .desc{
            height:150px;
        }
        li{
            padding-top: 15px;
        }
        .titolo{
            font-size: 22px;
        }
    </style>

</head>
<body>
    <div class="container">
        <div class="item-left">
            <img src="/immagini/campoDaCalcio.jpg" width="360px" height="240px">
        </div>
        <div class="item-right">
            <ul class="list">
                <li class="titolo">CAMPO DA CALCIO</li>
                <li class="desc">Descrizione ....</li>
                <li><a class="btn" href="#Prenotazione">Prenota</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="item-left">
            <ul class="list">
                <li class="titolo">CAMPO DA PALLAVOLO</li>
                <li class="desc">Descrizione ....</li>
                <li><a class="btn" href="#Prenotazione">Prenota</a></li>
            </ul>
        </div>
        <div class="item-right">
            <img src="/immagini/campoDaCalcio.jpg" width="360px" height="240px">
        </div>
    </div>
    <div class="container">
        <div class="item-left">
            <img src="/immagini/campoDaCalcio.jpg" width="360px" height="240px">
        </div>
        <div class="item-right">
            <ul class="list">
                <li class="titolo">CAMPO DA TENNIS</li>
                <li class="desc">Descrizione ....</li>
                <li><a class="btn" href="#Prenotazione">Prenota</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="item-left">

            <ul class="list">
                <li class="titolo">PALESTRA</li>
                <li class="desc">Descrizione ....</li>
                <li><a class="btn" href="#Prenotazione">Abbonati</a></li>
            </ul>
        </div>
        <div class="item-right">
            <img src="/immagini/campoDaCalcio.jpg" width="360px" height="240px">
        </div>
    </div>

    <jsp:include page="/view/footer.jsp">
        <jsp:param name="title" value=""/>
    </jsp:include>
</body>
</html>
