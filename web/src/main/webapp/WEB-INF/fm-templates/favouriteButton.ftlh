<button class="btn" onclick="toFav(${event.id}, ${email})">
    <strong>
        <span id="${event.id}">
            &#9734;
        </span>
    </strong>
</button>


<script>
    /*
    function checkIfFav(eventID, email) {
        id = eventID;
        mail = email;
        console.log(mail);
        favAddress = '/api/favourite/' + mail + '/event/' + id;
        $.ajax({
            url: favAddress,
            type: "GET",
            success: function (result) {
                console.log('Success in checking event in favourites');
                isFavourite = result;
                console.log("result is:  " + result);
                if (isFavourite == true) {
                    document.getElementById(eventID).style.background = "#D3D3D3";
                }
                if (isFavourite == false) {
                    document.getElementById(eventID).style.background = "#3CB371";
                }
            },
            error: function (error) {
                console.log(error);
                window.alert('Błąd w trakcie sprawdzania wydarzenia czy jest ulubione');
            }
    }
    )
    }
    */

    function toFav(eventID, email) {
        id = eventID;
        mail = email;
        console.log(mail);
        favAddress = '/api/favourite/' + mail + '/event/' + id;
        $.ajax({
            url: favAddress,
            type: "GET",
            success: function (result) {
                console.log('Success in checking event in favourites');
                isFavourite = result;
                console.log("result is:  " + result);
                if (isFavourite == true) {
                    // document.getElementById(eventID).style.background = "#3CB371";
                    document.getElementById(eventID).innerHTML = "&#11088;";
                    /* typeOfAction = "DELETE";
                     $.ajax({
                             url: favAddress,
                             type: "DELETE",
                             success: function (result) {
                                 console.log('Success in  deleting from favourite');
                                 document.getElementById(eventID).style.background = "#D3D3D3";
                                 window.alert('Udało się usunąć wydarzenie z ulubionych');
                             },
                             error: function (error) {
                                 console.log(error);
                                 window.alert('Błąd w trakcie usuwania wydarzenia  z ulubionych');
                             }
                         }
                     )*/
                }
                if (isFavourite == false) {
                    typeOfAction = "POST";
                    $.ajax({
                            url: favAddress,
                            type: "POST",
                            success: function (result) {
                                console.log('Success in  adding  to favourite');
                                //document.getElementById(eventID).style.background = "#3CB371";
                                document.getElementById(eventID).innerHTML = "&#11088;";
                                window.alert('Udało się dodać wydarzenie do ulubionych');
                            },
                            error: function (error) {
                                console.log(error);
                                window.alert('Błąd w trakcie dodawania wydarzenia do ulubionych');
                            }
                        }
                    )

                }
                console.log("Generated type of HTTP communication based on result " + typeOfAction);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
</script>
