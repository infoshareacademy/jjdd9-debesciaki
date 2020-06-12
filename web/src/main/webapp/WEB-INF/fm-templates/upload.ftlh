<!DOCTYPE html>
<html lang="pl-PL">
<#include "headSection.ftlh">
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand " href="/">&nbsp;&nbsp;Planer kulturalny</a>
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
<br/>
<br/>
<div class="container">
    <div class="jumbotron ">
        <h1>Załaduj dane z REST API</h1>
        <form method="post" id="api" action="/upload-rest">
            <button class="btn btn-dark" type="submit" name="save"  form="api" value="Załaduj">Załaduj</button>
            <br/><br/>
        </form>
        <h1>Załaduj plik z bazą organizatorów</h1>
        <form method="post" id="org" action="/upload-org" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <button class="btn btn-dark" type="submit" name="save"  form="org" value="Zapisz">Zapisz</button>
            <br/><br/>
        </form>
        <h1>Załaduj plik z bazą miejsc</h1>
        <form method="post" id="pla" action="/upload-pla" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <button class="btn btn-dark" type="submit" name="save"  form="pla" value="Zapisz">Zapisz</button>
            <br/><br/>
        </form>
        <h1>Załaduj plik z bazą kategorii</h1>
        <form method="post" id="cat" action="/upload-cat" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <button class="btn btn-dark" type="submit" name="save"  form="cat" value="Zapisz">Zapisz</button>
            <br/><br/>
        </form>
        <h1>Załaduj plik z bazą wydarzeń</h1>
        <form method="post" id="eve" action="/upload-eve" enctype="multipart/form-data">
            Plik JSON: <input type="file" name="json"/><br/><br/>
            <button class="btn btn-dark" type="submit" name="save"  form="eve" value="Zapisz">Zapisz</button>
            <br/><br/>
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
