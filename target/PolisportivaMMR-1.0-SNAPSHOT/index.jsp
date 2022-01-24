
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Polisportiva MMR</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="immagini/logo.png">
    <style>
        <%@include file="css/header.css"%>
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


        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            width: 200px;
        }

        li a {
            display: block;
            color: #000;
            text-decoration: none;
        }

        /* Change the link color on hover */
        li a:hover {
            background-color: #555;
            color: white;
        }
        /* On smaller screens, decrease text size */
        @media only screen and (max-width: 300px) {
            .prev, .next,.text {font-size: 11px}
        }

    </style>


</head>
<body>

<div class="header">

    <a href="#home" class="logo"><img src="immagini/Logo.png" width="100px" height="100px"></a>
    <div class="header-center">
        <a class="notLast" href="#abbonamenti">Abbonamenti</a>
        <a class="notLast" href="#prenota">Prenota</a>
        <a href="#campi">Campi</a>
    </div>

    <div class="header-right">
        <a class="button" href="#login"> LOGIN</a>
    </div>
</div>

<div class="descrizione">
    <h2>Vieni a scoprire la nostra polisportiva...</h2>
    <p>LA POLISPORTIVA MMR INTENDE OFFRIRE AI PROPRI CLIENTI UN SERVIZIO <br> COMODO ED EFFICIENTE PER TUTTE LE ESIGENZE SPORTIVE<br>
    PRENOTARE UN CAMPO NON E' MAI STATO COSI SEMPLICE CON IL FORM ONLINE, <br> COSI' COME ACQUISTARE ABBONAMENTI PERIODICI PER LA PALESTRA </p>
</div>
<div class="slideshow-container">

    <div class="mySlides fade">
        <img src="immagini/campoDaPallavolo.jpg" style="width:100%">
        <div class="text">Campo da Pallavolo</div>
    </div>

    <div class="mySlides fade">
        <img src="immagini/campoDaTennis.JPG" style="width:100%">
        <div class="text">Campo da tennis</div>
    </div>

    <div class="mySlides fade">
        <img src="immagini/campoDaCalcio.jpg" style="width:100%">
        <div class="text">Campo da calcio</div>
    </div>

    <div class="mySlides fade">
        <img src="immagini/palestra.jpg" style="width:100%">
        <div class="text">Palestra</div>
    </div>

    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>

</div>

<script>
    var slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        if (n > slides.length) {slideIndex = 1}
        if (n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }

        slides[slideIndex-1].style.display = "block";
    }
</script>

<div class="header">

    <a href="#home" class="logo"><img src="immagini/Logo.png" width="100px" height="100px"></a>
    <div class="header-center">
        <p>MAIN MENU</p>
        <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="#news">Prenota</a></li>
            <li><a href="#contact">Campi</a></li>

        </ul>
    </div>

    <div class="header-right">
        <p>FOLLOW US ON</p>
        <ul>
            <li><a href="#Facebook">Facebook</a></li>
            <li><a href="#Twitter">Twitter</a></li>
            <li><a href="#Instagram">Instagram</a></li>

        </ul>
    </div>
</div>


</body>
</html>