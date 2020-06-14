<#if role == "USER" || role == "ADMIN" || role == "SUPER_ADMIN" >
    <div id="signupContainer" class="flex-container">
        <br>
        <table>
            <tr>
                <th class="th-sm text-left" width="95%">
                    <p id="sneak-peak-text"></p>
                    <p id="sneak-start"></p>
                    <p id="sneak-end"></p>
                </th>
                <th class="th-sm text-top text-right" width="5%">
                    <button type="button" class="btn btn-dark" id="closeSignup" style="text-decoration-color: red">&#10060;</button>
                </th>
            </tr>
        </table>
    </div>
    <script>
        document.getElementById("signupContainer").style.backgroundColor = "#add8e6";
        const signupContainer = document.querySelector('#signupContainer')
        const closeSignup = document.querySelector('#closeSignup')
        closeSignup.addEventListener('click', () => signupContainer.style.display = 'none')
        address = "/api/sneak-peak/" + "${email}";
        $.ajax({
            url: address,
            type: "GET",
            success: function (result) {
                if (result.name == "NONE"){
                    signupContainer.style.display = 'none';
                }else{
                    document.getElementById("sneak-peak-text").innerHTML = "Nadchodzące ulubione wydarzenie: " + result.name;
                    document.getElementById("sneak-start").innerHTML = "Data rozpoczęcia: " + result.startDate;
                    document.getElementById("sneak-end").innerHTML = "Data zakończenia: " + result.endDate;
                }
            },
            error: function (error) {
                signupContainer.style.display = 'none';
                console.log(error);
            }
        })
    </script>
</#if>