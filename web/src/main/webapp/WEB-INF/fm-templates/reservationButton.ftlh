<button class="btn btn-success" onclick="sendReservationRequest(${event.id}, '${email}')">
Zarezerwuj
</button>

<script>
    function sendReservationRequest(eventId, mail){
        addressForRequest ="/api/request-reservation/"+mail+"/"+eventId;
        console.log(addressForRequest)
        $.ajax({
            url: addressForRequest,
            type: "GET",
            success: function (result) {
                console.log(result);
                window.alert(result);
            },
            error: function (error) {
                console.log(error);
                window.alert("Błąd przy wysyłaniu zapytania o rezerwację");
            }
        })
    }
</script>