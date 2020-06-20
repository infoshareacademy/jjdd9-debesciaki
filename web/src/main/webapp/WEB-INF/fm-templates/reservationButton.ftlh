Ulgowy <input type="checkbox" id ="isRedu" value="Ulgowy" aria-label="Ulgowy" name="Ulgowy"><br/>
<button class="btn btn-success" onclick="sendReservationRequest(${event.id}, '${email}')">
Zarezerwuj
</button>

<script>
    function sendReservationRequest(eventId, mail){
        redu = document.getElementById("isRedu").checked;
        if (redu==true){
            addressForRequest ="/api/request-reservation/"+mail+"/r/"+eventId;
        }else{
            addressForRequest ="/api/request-reservation/"+mail+"/f/"+eventId;
        }

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