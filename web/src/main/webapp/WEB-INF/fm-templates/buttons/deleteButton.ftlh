<button class="btn" onclick="deleteEvent(${event.id})">
   <span><i class='far fa-trash-alt'></i></span>
</button>
<script>
   function deleteEvent(eventID) {
      id = eventID;
      $.ajax({
         url: '/api/events/' + id,
         type: "DELETE",
         success: function (result) {
            console.log('Success in deleting event');
            window.alert('Udało się usunąć wydarzenie z bazy danych.');
         },
         error: function (error) {
            console.log(error);
         }
      })
   }
</script>