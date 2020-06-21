<table class="table">
    <thead class="thead-dark">
    <tr>
        <th class="th-sm text-center" scope="col" width="25%">
            <#if event.ticket == "bilety">
                Ulgowy <input type="checkbox" id="isRedu" value="Ulgowy" aria-label="Ulgowy" name="Ulgowy">
            </#if>
        </th>
        <th class="th-sm text-center" scope="col" width="25%">
            <button class="btn btn-success" onclick="sendReservationRequest(${event.id}, '${email}')">
                Zarezerwuj
            </button>
        </th>
        <th class="th-sm text-center" scope="col" id="messageDuringAction" width="50%">

        </th>
    </tr>
    </thead>
</table>


<script>
    function sendReservationRequest(eventId, mail) {
        redu = document.getElementById("isRedu").checked;
        if (redu == true) {
            addressForRequest = "/api/request-reservation/" + mail + "/r/" + eventId;
        } else {
            addressForRequest = "/api/request-reservation/" + mail + "/f/" + eventId;
        }
        document.getElementById("messageDuringAction").innerHTML = "Przetwarzanie żądania";
        console.log(addressForRequest)
        $.ajax({
            url: addressForRequest,
            type: "GET",
            success: function (result) {
                document.getElementById("messageDuringAction").innerHTML = "";
                console.log(result);
                window.alert(result);
            },
            error: function (error) {
                document.getElementById("messageDuringAction").innerHTML = "";
                console.log(error);
                window.alert("Błąd przy wysyłaniu zapytania o rezerwację");
            }
        })
    }
</script>