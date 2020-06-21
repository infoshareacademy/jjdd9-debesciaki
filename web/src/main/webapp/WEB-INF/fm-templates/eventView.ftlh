<div class="container">
    <#include "sneakPeak.ftlh">
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
                    <#if event.ticket == "wstęp wolny"><span class="font-weight-bold"><br>Wstęp wolny.</span></#if>
                    <#if event.ticket == "bilety"><span
                            class="font-weight-bold"><br>Ceny biletów: </span> ${event.minTicketPrice!'brak informacji'} - ${event.maxTicketPrice!'brak informacji'}</#if>
                    <#if event.ticket == "brak informacji"><span class="font-weight-bold"><br>Brak informacji o biletach</span></#if>
                    <#if event.website != "Brak strony internetowej"><span class="font-weight-bold"><br>Strona internetowa: </span>
                        <a href="${event.website}">${event.website}</a></#if>
                    <#if event.facebook != "Brak profilu na facebooku"><span
                            class="font-weight-bold"><br>Facebook: </span><a
                        href="${event.facebook}">${event.facebook}</a></#if>
                    <span class="font-weight-bold text-center"><br>Opis wydarznia: </span><br>
                    <p>${event.descLong?no_esc}</p>
                    <#if event.ticket == "bilety"  && (role == "USER" || role == "ADMIN" || role == "SUPER_ADMIN")>
                        <br/>
                        <#include "reservationButton.ftlh">
                    </#if>
                </td>
            </tr>
        </table>
        <h6 class="text-right"><a href="${previous}" class="text-primary font-italic">Powrót do listy wydarzeń</a></h6>
    </div>
</div>