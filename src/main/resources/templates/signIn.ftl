<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<#if error??>
    <div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
</#if>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Login!
    </div>

    <form method="post" action="/login">
        <input name="username" placeholder="Почта">
        <br>
        <input type="password" name="password" placeholder="Пароль">
        <br>
        <input type="submit" value="Вход">
    </form>
</div>
</body>
</html>