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
                <li class="nav-item">
                    <a class="nav-link" href="/">Strona główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show-events?action=showAll&page=1">Wydarzenia
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show-organizers?page=1">Organizatorzy</a>
                </li>
                <#include "loginLogout.ftlh">
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron ">
        <h1>Załaduj dane z REST API</h1>
        <form method="post" action="/upload-rest">
            <input type="submit" name="save" value="Załaduj"/><br/><br/>
        </form>
        <h1>Załaduj plik z bazą organizatorów</h1>
        <form method="post" action="/upload-org" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <input type="submit" name="save" value="Zapisz"/><br/><br/>
        </form>
        <h1>Załaduj plik z bazą miejsc</h1>
        <form method="post" action="/upload-pla" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <input type="submit" name="save" value="Zapisz"/><br/><br/>
        </form>
        <h1>Załaduj plik z bazą kategorii</h1>
        <form method="post" action="/upload-cat" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <input type="submit" name="save" value="Zapisz"/><br/><br/>
        </form>
        <h1>Załaduj plik z bazą wydarzeń</h1>
        <form method="post" action="/upload-eve" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <input type="submit" name="save" value="Zapisz"/><br/><br/>
        </form>
    </div>
</div>
<footer class="footer py-3 bg-dark fixed-bottom">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Bartłomiej Trawiński & Jakub Cichacki 2020</p>
    </div>
</footer>

<script src="/bootstrap/vendor/jquery/jquery.min.js"></script>
<script src="/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
