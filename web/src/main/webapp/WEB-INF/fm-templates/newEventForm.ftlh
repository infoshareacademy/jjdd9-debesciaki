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
                <li class="nav-item ">
                    <a class="nav-link" href="/show-events?action=showAll&page=1">Wydarzenia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show-organizers?page=1">Organizatorzy</a>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Panel administratora<span class="sr-only">(current)</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/upload.html">Załaduj bazę</a>
                        <a class="dropdown-item" href="/users-list">Lista użytkowników</a>
                        <a class="dropdown-item" href="/add-event">Dodaj wydarzenie</a>
                        <a class="dropdown-item" href="#">Statystyki</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logoutGoogle">Wyloguj się</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br><br>
<div class="container">
    <div class="jumbotron">
        <h1 class="display-4">Formularz dodawania nowego wydarzenia</h1><br>
        <form method="post" action="/add-event">
            <span class="font-weight-bold">Nazwa wydarzenia: <input type="text" name="name" class="m-2" required/></span><br>
            <span class="font-weight-bold">Organizator: <input type="text" name="organizer" class="m-2" required/></span><br>
            <span class="font-weight-bold">Miejsce: <input type="text" name="place" class="m-2" required/></span><br>
            <span class="font-weight-bold">Adres strony internetowej: <input type="url" name="url" class="m-2"/></span><br>
            <span class="font-weight-bold">Data rozpoczęcia: <input type="datetime-local" name="startDate" class="m-2" required/></span><br>
            <span class="font-weight-bold">Data zakończenia: <input type="datetime-local" name="endDate" class="m-2" required/></span><br>
            <nobr><span class="font-weight-bold" >Typ biletów:
                <select class="m-2" id="selectBox">
                    <option value="free" name="free">bezpłatne</option>
                    <option value="tickets" name="tickets">płatne</option>
                </select></span></nobr><br>
            <span class="font-weight-bold">Opis: <input type="text" name="descLong" class="m-2"/></span><br>
            <button type="submit" class="btn btn-primary btn-lg" >Zapisz</button>
        </form>
    </div>
</div>
<div id="dialog-modal">test</div>
<#include "footerWithScripts.ftlh">
<script>
    $(document).ready(function () {

        $('#dialog-modal').dialog({
            modal: true,
            autoOpen: false
        });

        $('#selectBox').change(function () {
            if ($(this).val() == 'tickets') {
                $('#dialog-modal').dialog('open');
            }
        });

    });
</script>
</body>
</html>