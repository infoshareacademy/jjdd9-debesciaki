<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Dodaj organizatora</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closeButton">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" >
                    <span class="font-weight-bold">Wpisz nazwę organizatora:<br>
                        <input id="designation" type="text" name="designation" class="m-2" required/></span><br>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                <button type="button" class="btn btn-primary" onclick="f()" name="save">Zapisz</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>

    $('#myModal').keypress(function (e) {
        const key = e.which;
        if (key == 13) {
            $('button[name = save]').click();
            return false;
        }
    });

    function f() {
        designation = $("#designation").val();
        address = '/api/organizers';
        $.ajax({
            url: address,
            async: true,
            type: "POST",
            data: {"designation":designation},
            success: function() {
                console.log('Success');
                $("#organizersDesignation").append(new Option(designation, designation));
                $("#organizersDesignation").val(designation).change();
                $("#closeButton").click();
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
</script>