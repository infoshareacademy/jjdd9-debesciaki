<!DOCTYPE html>
<html lang="pl-PL">
<#include "headSection.ftlh">
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
                <li class="nav-item ">
                    <a class="nav-link" href="/show-events?action=showAll&page=1">Wydarzenia</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#">Organizatorzy
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Zaloguj się</a>
                </li>
                <li class="nav-item">
                    <a>
                        <#noautoesc>
                            <form method="get" action="/show-events">
                                <button class="btn btn-primary"
                                        name="action"
                                        type="submit"
                                        value="search"
                                        style="float: right">
                                    Szukaj
                                </button>
                                <input type="hidden" name="page" value="1"/>
                                <div style="overflow: hidden; padding-right: .5em;">
                                    <input type="text" name="phrase" style="width: 100%;"/>
                                </div>
                            </form>
                        </#noautoesc>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br><br>
<#include "organizersList.ftlh">
<#include "footerWithScripts.ftlh">
</body>
</html>