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
<br><br>
<div class="container">
    <div class="jumbotron ">
        <h1 class="display-5 text-center text-primary">${event.name}</h1>
        <h6><p class="display-6 lead text-center font-italic">${event.organizerName}</p></h6>
        <hr class="my-4">
        <table class="table table-borderless mb-1">
            <tr>
                <td width="300px">
                    <img src="${event.fileName}" class="img-thumbnail rounded float-left mr-4">
                </td>
                <td>
                    <span class="font-weight-bold">Miejsce: </span>${event.placeName}<#if event.placeSubname != "brak">, ${event.placeSubname}</#if>
                    <span class="font-weight-bold"><br>Kategoria: </span>${event.categoryName}
                    <span class="font-weight-bold"><br>Data rozpoczęcia: </span>${event.startDate}
                    <span class="font-weight-bold"><br>Data zakończenia: </span>${event.endDate}
                    <span class="font-weight-bold"><br>Adres: </span><#if event.placeStreet??>${event.placeStreet} </#if><#if event.placeZipcode??>,${event.placeZipcode} </#if><#if event.placeCity??>${event.placeCity}</#if>
                    <#if event.ticket == "wstęp wolny"><span class="font-weight-bold"><br>Wstęp wolny.</span></#if>
                    <span class="font-weight-bold"><br>Liczba dostępnych miejsc: </span>${event.numberOfTickets}
                    <#if event.ticket == "bilety"><span class="font-weight-bold"><br>Cena biletu normalnego: </span> ${event.maxTicketPrice}</#if>
                    <#if event.ticket == "bilety"><span class="font-weight-bold"><br>Cena biletu ulgowego: </span> ${event.minTicketPrice}</#if>
                    <#if event.website != "Brak strony internetowej"><span class="font-weight-bold"><br>Strona internetowa: </span><a href="${event.website}">${event.website}</a></#if>
                    <#if event.facebook != "Brak profilu na facebooku"><span class="font-weight-bold"><br>Facebook: </span><a href="${event.facebook}">${event.facebook}</a></#if>
                    <span class="font-weight-bold text-center"><br>Opis wydarznia: </span><br>
                    <p>${event.descLong?no_esc}</p>
                </td>
            </tr>
        </table>
        <h6 class="text-right"><a href="${previous}" class="text-primary font-italic">Powrót do listy wydarzeń</a></h6>
    </div>
</div>
<#include "footerWithScripts.ftlh">
</body>
</html>