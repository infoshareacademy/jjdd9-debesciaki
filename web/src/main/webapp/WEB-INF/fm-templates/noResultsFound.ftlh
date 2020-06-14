<!DOCTYPE html>
<html lang="pl-PL">
<#include "headSection.ftlh">
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand " href="/">&nbsp;&nbsp;<#include "logo.ftlh"></a>
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
                    <a class="nav-link" href="/show-events?action=showAll&page=1">Wydarzenia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show-organizers?page=1">Organizatorzy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Zaloguj się</a>
                </li>
                <#include "loginLogout.ftlh">
            </ul>
        </div>
    </div>
</nav>


<br><br>
<div class="container">
    <#include "sneakPeak.ftlh">
    <div class="jumbotron ">
        <h3 class="display-4 text-center">Lista nadchodzących wydarzeń</h3>
        <br>
        <br>
        <br>
        <p>
            Nie znaleziono wydarzenia dla frazy: ${phrase}
        </p>
        <h6 class="text-right"><a href="${previous}" class="text-primary font-italic">Powrót do poprzedniej strony</a></h6>
    </div>
</div>
<#include "footerWithScripts.ftlh">
</body>
</html>