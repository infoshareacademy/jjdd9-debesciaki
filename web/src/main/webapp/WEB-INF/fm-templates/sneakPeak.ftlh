<#if role == "USER" || role == "ADMIN" || role == "SUPER_ADMIN" >
    <div id="signupContainer" class="flex-container">
        <br>
        <table>
            <tr>
                <th class="th-sm text-left" width="99%">
                    <p style="font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';font-size:16px;font-style:italic;"
                       id="sneak-peak-text"></p>
                </th>
                <th class="th-sm justify-content-around" width="1%">
                    <button type="button" class="btn btn-dark" id="closeSignup" style="text-decoration-color: red">
                        &#10060;
                    </button>
                </th>
            </tr>
            <tr>
                <td>
                    <p style="font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';font-size:16px;font-style:italic;"
                       id="sneak-date"></p></td>
                <td></td>
            </tr>
        </table>
    </div>
    <script>
        document.getElementById("signupContainer").style.backgroundColor = "#ADEFD1FF";
        const signupContainer = document.querySelector('#signupContainer');
        const closeSignup = document.querySelector('#closeSignup');
        closeSignup.addEventListener('click', () => signupContainer.style.display = 'none');
        closeSignup.addEventListener('click', () => sessionStorage.setItem('exclude', true));
        exclusion = sessionStorage.getItem('exclude');
        if (exclusion == "true") {
            signupContainer.style.display = 'none';
        } else {
            address = "/api/sneak-peak/" + "${email}";
            $.ajax({
                url: address,
                type: "GET",
                success: function (result) {
                    if (result.name == "NONE") {
                        signupContainer.style.display = 'none';
                    } else {
                        document.getElementById("sneak-peak-text").innerHTML = "Nadchodzące ulubione wydarzenie: " + result.name;
                        document.getElementById("sneak-date").innerHTML = "Data rozpoczęcia: " + result.startDate + "  Data zakończenia: " + result.endDate;
                    }
                },
                error: function (error) {
                    signupContainer.style.display = 'none';
                    console.log(error);
                }
            })
        }
    </script>
</#if>