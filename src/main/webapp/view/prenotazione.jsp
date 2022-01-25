<%--
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
        }
        .selector,.elenco{
            display: flex;
            flex: 50%;
            justify-content: center;
            align-items: center;
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
        }
        #campo,#dataDiv,#oraDiv{
            display: flex;
            flex: 50%;
            justify-content: center;
            align-items: center;
            margin-top: 10px;
        }


    </style>

<body>
<label id="title">PRENOTA IL CAMPO</label>
<div class="container">
<div class="selector">
    <div id="campo">
    <label>Seleziona Campo:  </label>
    <select class="campo" required>
        <option>Calcio</option>
        <option>Pallavolo</option>
        <option>Tennis</option>
    </select>
    </div>

    <div id="dataDiv">
        <label>Seleziona Data:  </label>
        <input type="date" id="data">
    </div>

    <div id="oraDiv">
        <label>Seleziona Ora:  </label>
        <select class="oraStart" required>
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
    </div>
</div>
<div class="elenco">
    <span>
        <label>qui andrebbero inserite le prenotazioni che rispecchiano i canoni , vanno inserite programmaticamente</label>
    </span>
</div>
</div>

</body>
</html>
