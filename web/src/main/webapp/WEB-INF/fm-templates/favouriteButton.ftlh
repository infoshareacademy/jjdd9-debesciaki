<button class="btn" onclick="toFav(${event.id}, ${emailFav})">
    <strong>
        <span id="${event.id}">
            &#9734;
        </span>
    </strong>
</button>


<script>
    mailS = "${emailFav}";
    mailS = mailS.slice(6, -6);
    console.log(mailS);
    favAddressS = '/api/favourite/' + mailS + '/event/' + ${event.id};
    $.ajax({
        url: favAddressS,
        type: "GET",
        success: function (result) {
            console.log('Success in checking event in favourites');
            isFavouriteS = result;
            console.log("Is event " + ${event.id} +" in favourites? :  " + result);
            if (isFavouriteS == true) {
                document.getElementById(${event.id}).innerHTML = "&#11088;";
            }
        },
        error: function (error) {
            console.log(error);
        }
    })


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
                console.log("Is event in favourites? :  " + result);
                if (isFavourite == true) {
                    typeOfAction = "DELETE";
                    $.ajax({
                            url: favAddress,
                            type: "DELETE",
                            success: function (result) {
                                console.log('Success in  deleting from favourite');
                                window.alert('Udało się usunąć wydarzenie z ulubionych');
                            },
                            error: function (error) {
                                console.log(error);
                                window.alert('Błąd w trakcie usuwania wydarzenia  z ulubionych');
                            }
                        }
                    )
                    document.getElementById(eventID).innerHTML = "&#9734;";
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
