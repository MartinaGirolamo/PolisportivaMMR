<!DOCTYPE html>
<html>
<head>
    <title>LogIn</title>
    <script src="https://kit.fontawesome.com/72e0d6ca25.js" crossorigin="anonymous"></script>
    <style><%@include file="/css/login.css"%></style>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>



<form action="../ServletLogin" method="post">
    <h2 style="text-align:center">LOGIN FORM</h2>

    <div class="container">
        <label for="email" id="email"><b>EMAIL</b></label>
        <input type="text" placeholder="Enter email" name="email" required>

        <label for="psw" id="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit">Login</button>

        <p class="new">NUOVO UTENTE?  <a href="register.jsp">ISCRIVITI!</a></p>

    </div>

</form>
</body>
</html>