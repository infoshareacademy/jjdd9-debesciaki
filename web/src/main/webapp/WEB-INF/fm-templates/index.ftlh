<!DOCTYPE html>
<html lang="pl-PL">
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
                <li class="nav-item active">
                    <a class="nav-link" href="#">Strona główna
                        <span class="sr-only">(current)</span>
                    </a>
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
            </ul>
        </div>
    </div>
</nav>
<#include "searchInput.ftlh">

<div class="container">
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="../../bootstrap/img/gdansk.jpg" class="d-block w-100" alt="Gdańska">
            </div>
            <div class="carousel-item">
                <img src="../../bootstrap/img/panoramaGdanska.jpg" class="d-block w-100" alt="Panorama Gdańska">
            </div>
            <div class="carousel-item">
                <img src="../../bootstrap/img/panoramaNeptun.jpg" class="d-block w-100" alt="Panorama Neptun">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="jumbotron">
        <h1 class="display-4">Wydarzenia kulturalne w Gdańsku</h1>
        <p class="lead">Witaj serdecznie na naszej stronie internetowej, prezentującej nadchodzące wydarzenia w Gdańsku!<br>Znajdziesz
            tutaj wszystkie informacje dotyczące imprez w naszym mieście - kto, gdzie, kiedy.<br>Co więcej, sprawdzisz
            czy są jeszcze dostępne bilety, a po zalogowaniu będziesz miał możliwość je zakupić.</p>
        <nobr><a href="/show-events?action=showAll&page=1" class="btn btn-primary btn-lg">Sprawdź więcej</a>&nbsp</nobr>
    </div>
</div>
<#include "footerWithScripts.ftlh">
</body>
</html>