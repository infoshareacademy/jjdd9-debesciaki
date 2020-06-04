<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Projekt Debeściaki - Infoshare Academy 2020">
    <meta name="author" content="Bartłomiej Trawiński, Jakub Cichacki">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content=982683700163-ed48vnuiru6qtim5k45qjhn80jrqkcip.apps.googleusercontent.com>
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <title>Projekt Debeściaki - Wydarzenia kulturalne w Gdańsku</title>

    <link href="../../bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../bootstrap/css/heroic-features.css" rel="stylesheet">
    <link rel="stylesheet" href="../../bootstrap/css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand " href="/index">&nbsp;&nbsp;Projekt Debeściaki - Infoshare Academy</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/index">Strona główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="show-events?action=showAll&page=1">Wydarzenia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="show-organizers?page=1">Organizatorzy</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#">Zaloguj się
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<#-- ftlh for  search navbar
<#include "searchInput.ftlh">-->

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <form class="form-signin">
                        <div class="text-center"><img src="../../bootstrap/img/logo-google.svg" alt="google"></div>
                        <span class="m-2"><br></span>
                        <div class="g-signin2 align-content-center" data-onsuccess="onSignIn"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<#include "footerWithScripts.ftlh">
</body>
</html>