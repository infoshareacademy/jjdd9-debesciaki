<button id="${event.id}" class="btn" onclick="toFav(${event.id}, ${email})" >
    <strong>
        &#11088;
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
                    typeOfAction = "DELETE";
                    $.ajax({
                            url: favAddress,
                            type: "DELETE",
                            success: function (result) {
                                console.log('Success in  deleting from favourite');
                                window.alert('Udało się usunąć wydarzenie z ulubionych');
                                document.getElementById(eventID).style.background = "#D3D3D3";
                            },
                            error: function (error) {
                                console.log(error);
                                window.alert('Błąd w trakcie usuwania wydarzenia  z ulubionych');
                            }
                        }
                    )
                }
                if (isFavourite == false) {
                    typeOfAction = "POST";
                    $.ajax({
                            url: favAddress,
                            type: "POST",
                            success: function (result) {
                                console.log('Success in  adding  to favourite');
                                window.alert('Udało się dodać wydarzenie do ulubionych');
                                document.getElementById(eventID).style.background = "#3CB371";
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
