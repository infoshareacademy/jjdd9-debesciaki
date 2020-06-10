<label for="myCheck">Checkbox:</label>
<input type="checkbox" id="myCheck" onclick="actOnClick()">

<p id="text" style="display:none">Checkbox is CHECKED!</p>

<script>
    function actOnClick() {
        var checkBox = document.getElementById("myCheck");
        var text = document.getElementById("text");
        if (checkBox.checked == true) {
            text.style.display = "block";
        } else {
            text.style.display = "none";
        }
    }

    function addToFav() {
        id = ${event.id};
        mail = 'bartraw23@gmail.com';

        $.ajax({
            url: res,
            type: "GET",
            success: function (result) {
                console.log('Success in converting data');
                results = result;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }

</script>
