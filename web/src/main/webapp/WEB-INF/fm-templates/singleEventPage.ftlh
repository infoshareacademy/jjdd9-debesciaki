<!DOCTYPE html>
<html lang="pl-PL">
<#include "headSection.ftlh">
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand " href="#">&nbsp;&nbsp;Projekt Debeściaki - Infoshare Academy</a>
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
                    <a class="nav-link" href="#">Wydarzenia
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Organizatorzy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Zaloguj się</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<#-- ftlh for  search navbar-->
<#include "searchInput.ftlh">

<br><br>
<#include "eventView.ftlh">
<#include "footerWithScripts.ftlh">
</body>
</html>