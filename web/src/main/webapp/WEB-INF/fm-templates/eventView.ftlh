<div class="container">
    <div class="jumbotron ">
        <h3 class="display-4 text-center">${event.name}</h3><br><br>
        <p class="lead text-center">${event.organizerName}</p>
        <hr class="my-4">
        <p class = "text-center">${event.descShort}</p>

    </div>
    <div class = "container">
        <div class = "row">
            <div class = "col-4 align-content-center">
                <img src="${event.fileName}">
            </div>
            <div class = "col-8">
                <span class="font-weight-bold">Miejsce: </span>${event.placeName}<br>
                <span class="font-weight-bold">Data rozpoczęcia: </span>${event.startDate}<br>
                <span class="font-weight-bold">Data zakończenia: </span>${event.endDate}<br><br>
                <span class="font-weight-bold text-center">Opis wydarznia: </span><br>
                <p>${event.descLong}</p>
            </div>
        </div>
    </div>
</div>