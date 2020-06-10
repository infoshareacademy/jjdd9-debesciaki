<button class ="btn btn-dark" id="myCheck" onclick="actOnClick(${event.id})"></button>
<#--
<p id="text" style="display:none">Checkbox is CHECKED!</p>
-->

<script>
    function actOnClick(eventID) {
        toFav(eventID);
    }

    function toFav(eventID) {
        id = eventID;
        mail = 'bartraw23@gmail.com';
        favAddress = '/api/favourite/' + mail + '/event/' + id;
        $.ajax({
            url: favAddress,
            type: "GET",
            success: function (result) {
                console.log('Success in checking event in favourites');
                isFavourite = result;
                console.log("result is:  "+result);
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
                }
                if (isFavourite == false) {
                    typeOfAction = "POST";
                    $.ajax({
                            url: favAddress,
                            type: "POST",
                            success: function (result) {
                                console.log('Success in  adding  to favourite');
                                window.alert('Udało się dodać wydarzenie do ulubionych');
                            },
                            error: function (error) {
                                console.log(error);
                                window.alert('Błąd w trakcie dodawania wydarzenia do ulubionych');
                            }
                        }
                    )

                }
                console.log("Generated type of HTTP communication based on result "+typeOfAction);

            },
            error: function (error) {
                console.log(error);
            }
        })
    }

    /* var checkBox = document.getElementById("myCheck");
     var text = document.getElementById("text");
     if (checkBox.checked == true) {
     text.style.display = "block";
     } else {
     text.style.display = "none";
     }*/

</script>
