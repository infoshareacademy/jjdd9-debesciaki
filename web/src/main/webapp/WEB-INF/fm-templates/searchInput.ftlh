<#noautoesc>
    <form autocomplete="off" action="/show-events">
        <div class="autocomplete" style="width:30%;">
            <input type="hidden" value="search" name="action">
            <input type="hidden" name="page" value="1"/>
            <input id="phrase" type="text" name="phrase" required minlength="3" placeholder="Nazwa">
        </div>
        <input type="submit" value="Szukaj">
        <div>
            <div class="custom-control custom-checkbox">
                <input type="checkbox" id="checkEve" onclick="buttonCheckEve()" checked>
                <input type="hidden" id="checkEveVal" name="eve" value="1">
                <label for="checkEve"> Nazwy wydarzeń </label>
            </div>

            <div class="custom-control custom-checkbox">
                <input type="checkbox" id="checkOrg" onclick="buttonCheckOrg()">
                <input type="hidden" id="checkOrgVal" name="org" value="0">
                <label for="checkOrg"> Nazwy organizatorów </label>
            </div>

            <div class="custom-control custom-checkbox">
                <input type="checkbox" id="checkDate" onclick="buttonCheckDate()">
                <input type="hidden" id="checkDateVal" name="date" value="0">
                <label for="checkDate"> Przedział czasowy </label>
            </div>

            <div>
                <label for="start">Data rozpoczęcia:</label>
                <input type="date" id="start" name="start" value="2020-06-01">
            </div>
            <div>
                <label for="end">Data zakończenia:</label>
                <input type="date" id="end" name="end" value="2021-06-01">
            </div>

        </div>
    </form>
    <script type="text/javascript" src="autocomplete.js"></script>
    <script type="text/javascript" src="eventJsonToArrayOfNames.js"></script>
    <script>
        orgBool = new Boolean(true);
        eveBool = new Boolean(true);
        dateBool = new Boolean(false);

        document.getElementById("phrase").addEventListener("keyup", myFunction);

        function myFunction() {
            if (document.getElementById("phrase").textLength > 2) {
                arr2 = [];
                eventJsonToArrayOfNames($(document).ready(function () {
                    str1 = "http://localhost:8080/api/search/eve/";
                    res = str1;

                    if (orgBool == true && eveBool == true) {
                        str1 = "http://localhost:8080/api/search/eve-org/";
                    } else if (orgBool == false && eveBool == true) {
                        str1 = "http://localhost:8080/api/search/eve/";
                    } else if (orgBool == true && eveBool == false) {
                        str1 = "http://localhost:8080/api/search/org/";
                    }
                    str2 = str1.concat(document.getElementById("phrase").value);
                    res = str2;

                    if (dateBool == true) {
                        str3 = str2.concat("/" + document.getElementById("start").value +
                            " 00:00:00/" + document.getElementById("end").value + " 23:59:59");
                        console.log(document.getElementById("start").value);
                        console.log(document.getElementById("end").value);
                        res = str3;
                    }

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
                }));

                autocomplete(document.getElementById("phrase"), results, arr2);
            }
        }

        function buttonCheckOrg() {
            var checkBox = document.getElementById("checkOrg");
            if (checkBox.checked == true) {
                console.log('Organizers filtering is on');
                orgBool = true;
                document.getElementById("checkOrgVal").value = 1;
            } else {
                console.log('Organizers filtering is off');
                orgBool = false;
                document.getElementById("checkOrgVal").value = 0;
            }
        }

        function buttonCheckEve() {
            var checkBox = document.getElementById("checkEve");
            if (checkBox.checked == true) {
                console.log('Event names filtering is on');
                eveBool = true;
                document.getElementById("checkDateVal").value = 1;
            } else {
                console.log('Event names filtering is off');
                eveBool = false;
                document.getElementById("checkDateVal").value = 0;
            }
        }

        function buttonCheckDate() {
            var checkBox = document.getElementById("checkDate");
            if (checkBox.checked == true) {
                console.log('Date filtering is on');
                dateBool = true;
                document.getElementById("checkDateVal").value = 1;
            } else {
                console.log('Date filtering is off');
                dateBool = false;
                document.getElementById("checkDateVal").value = 0;
            }
        }

    </script>
</#noautoesc>

