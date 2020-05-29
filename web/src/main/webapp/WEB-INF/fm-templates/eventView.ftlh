<div class="container">
    <div class="jumbotron ">
        <h3 class="display-3 text-center">${event.name}</h3>
        <h3><p class="display-5 lead text-center font-italic">${event.organizerName}</p></h3>
        <hr class="my-4">
        <table class="table table-borderless mb-1">
            <tr>
                <td width="300px">
                    <img src="${event.fileName}" class="img-thumbnail rounded float-left mr-4">
                </td>
                <td>
                    <span class="font-weight-bold">Miejsce: </span>${event.placeName}
                    <span class="font-weight-bold"><br>Data rozpoczęcia: </span>${event.startDate}
                    <span class="font-weight-bold"><br>Data zakończenia: </span>${event.endDate}
                    <span class="font-weight-bold text-center"><br>Opis wydarznia: </span><br>
                    <p>${event.descLong?no_esc}</p>
                </td>
            </tr>
        </table>
        <h4 class="text-right"><a href="${previous}" class="text-primary font-italic">Powrót do listy wydarzeń</a></h4>
    </div>
</div>
