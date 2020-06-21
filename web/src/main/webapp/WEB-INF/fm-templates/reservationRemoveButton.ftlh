<button id="${event.token}" class="btn btn-danger" onclick="deleteFromReseserved(${event.token})">
    <strong>
        -
    </strong>
</button>


<script>
    function deleteFromReseserved(token) {
        console.log(token);
        deleteAddress = '/request-reservation/' + token;
        $.ajax({
                url: deleteAddress,
                type: "DELETE",
                success: function (result) {
                    console.log('Success in  deleting from reservations');
                    window.alert('Udało się usunąć wydarzenie z ulubionych');
                    location.replace("/show-reservations?page=1");
                },
                error: function (error) {
                    console.log(error);
                    window.alert('Błąd w trakcie usuwania wydarzenia  z rezerwacji');
                }
            }
        )
    }
</script>
