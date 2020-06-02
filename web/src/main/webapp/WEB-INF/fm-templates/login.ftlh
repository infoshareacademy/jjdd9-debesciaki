<!DOCTYPE html>
<html>
<#include "headSection.ftlh">
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand " href="/">&nbsp;&nbsp;Projekt Debeściaki - Infoshare Academy</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Strona główna</a>
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
                        <button class="btn btn-lg btn-google btn-block text-uppercase" type="submit"><i
                                    class="fab fa-google mr-2"></i> Zaloguj się z Google
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<#include "footerWithScripts.ftlh">
</body>
</html>