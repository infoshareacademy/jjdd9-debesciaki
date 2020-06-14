<button id="${event.id}" class="btn btn-danger" onclick="toFav(${event.id}, ${emailFav})" >
    <strong>
        -
    </strong>
</button>


<script>
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
                                location.replace("/show-favourites?page=1");
                            },
                            error: function (error) {
                                console.log(error);
                                window.alert('Błąd w trakcie usuwania wydarzenia  z ulubionych');
                            }
                        }
                    )
                }
                if (isFavourite == false) {
                    window.alert('Błąd w trakcie usuwania wydarzenia  z ulubionych');
                }
                console.log("Generated type of HTTP communication based on result " + typeOfAction);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
</script>
