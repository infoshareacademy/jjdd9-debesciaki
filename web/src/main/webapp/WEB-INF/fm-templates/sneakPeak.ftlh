<#if role == "USER" || role == "ADMIN" || role == "SUPER_ADMIN" >
    <div id="signupContainer" class="container">
        <button id="closeSignup">X</button>
        <p id="sneak-peak-text">wuj</p>
        <div id="sneak-start">szuj</div>
    </div>
    <script>
        document.getElementById("signupContainer").style.backgroundColor = "#FFFAAF";
        const signupContainer = document.querySelector('#signupContainer')
        const closeSignup = document.querySelector('#closeSignup')
        closeSignup.addEventListener('click', () => signupContainer.style.display = 'none')
        address = "/api/sneak-peak/" + "${email}";
        $.ajax({
            url: address,
            type: "GET",
            success: function (result) {
                document.getElementById("sneak-peak-text").innerHTML = result.name;
                document.getElementById("sneak-start").innerHTML = result.startDate;
            },
            error: function (error) {
                console.log(error);
            }
        })
    </script>
</#if>