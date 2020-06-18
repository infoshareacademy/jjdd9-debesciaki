<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Projekt Debeściaki - Infoshare Academy 2020">
    <meta name="author" content="Bartłomiej Trawiński, Jakub Cichacki">

    <title>Planer kulturalny</title>

    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            background: #007bff;
            background: linear-gradient(to right, #0062E6, #33AEFF);
            font-size: 16px;
        }

        .autocomplete {
            /*the container must be positioned relative:*/
            position: relative;
            display: inline-block;
        }

        input {
            border: 1px solid transparent;
            background-color: #f1f1f1;
            padding: 10px;
            font-size: 16px;
        }

        input[type=text] {
            background-color: #f1f1f1;
            width: 100%;
        }

        input[type=submit] {
            background-color: DodgerBlue;
            color: #fff;
        }

        .autocomplete-items {
            position: absolute;
            border: 1px solid #d4d4d4;
            border-bottom: none;
            border-top: none;
            z-index: 99;
            /*position the autocomplete items to be the same width as the container:*/
            top: 100%;
            left: 0;
            right: 0;
        }

        .autocomplete-items div {
            padding: 10px;
            cursor: pointer;
            background-color: #fff;
            border-bottom: 1px solid #d4d4d4;
        }

        .autocomplete-items div:hover {
            /*when hovering an item:*/
            background-color: #e9e9e9;
        }

        .autocomplete-active {
            /*when navigating through the items using the arrow keys:*/
            background-color: DodgerBlue !important;
            color: #ffffff;
        }

        .fixed-top-2 {
            margin-top: 10px;
        }

        .flex-grid {
            display: flex;
        }

        .col {
            flex: 1;
        }


        .container button#closeSignup {
            background: transparent;
            border: none;
            cursor: pointer;
        }

        /* Style the tab */
        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Style the buttons inside the tab */
        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current tablink class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand " href="/">&nbsp;&nbsp;<#include "logo.ftlh"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="">Strona główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show-events?action=showAll&page=1">Wydarzenia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show-organizers?page=1">Organizatorzy</a>
                </li>
                <#include "loginLogout.ftlh">
            </ul>
        </div>
    </div>
</nav>
<br><br>
<div class="jumbotron">

    <div class="tab">
        <button class="tablinks" onclick="openTab(event, 'Wydarzenia global')">
            Wydarzenia globalna popularność
        </button>
        <button class="tablinks" onclick="openTab(event, 'Organizatorzy global')">
            Organizatorzy, globalna popularność
        </button>
        <button class="tablinks" onclick="openTab(event, 'Wydarzenia period')">
            Wydarzenia, popularność okresowa
        </button>
        <button class="tablinks" onclick="openTab(event, 'Organizatorzy period')">
            Organizatorzy, popularność okresowa
        </button>
    </div>

    <div id="Wydarzenia global" class="tabcontent">
        <div class="container">
            <canvas id="myBarChart" width="100" height="55"></canvas>
        </div>
    </div>

    <div id="Organizatorzy global" class="tabcontent">
        <div class="container">
            <canvas id="myBarChart2" width="100" height="40"></canvas>
        </div>
    </div>

    <div id="Wydarzenia period" class="tabcontent">
        <tr>
            <th>
                <input type="date" id="date1" value="2020-06-01">
                <input type="time" id="time1" value="00:00:00">
                <br/>
                <input type="date" id="date2" value="2022-06-01">
                <input type="time" id="time2" value="23:59:59">
            </th>
            <th>
                <button class="btn btn-dark" onclick="load1()">Załaduj</button>
            </th>
        </tr>
        <div class="container">
            <canvas id="myBarChart3" width="100" height="55"></canvas>
        </div>
    </div>

    <div id="Organizatorzy period" class="tabcontent">
        <tr>
            <th>
                <input type="date" id="date3" value="2020-06-01">
                <input type="time" id="time3" value="00:00:00">
                <br/>
                <input type="date" id="date4" value="2022-06-01">
                <input type="time" id="time4" value="23:59:59">
            </th>
            <th>
                <button class="btn btn-dark" onclick="load2()">Załaduj</button>
            </th>
        </tr>
        <div class="container">
            <canvas id="myBarChart4" width="100" height="40"></canvas>
        </div>
    </div>

    <script src="/node_modules/chart.js/dist/Chart.bundle.js"></script>
    <script type="text/javascript" src="eventJsonToArrayOfNames.js"></script>
    <script type="text/javascript" src="formatLabel.js"></script>
    <script>
        chartHandler();

        chartHandler2();

        function chartHandler() {
            let res;
            let labels;
            let values;
            let colorsBarBodyRepo = [];
            let colorsBarHoverRepo = [];
            let colorsBarHoverBorderRepo = [];

            $.ajax({
                    url: "/api/view-stat/global",
                    type: "GET",
                    success: function (result) {
                        res = eventJsonToArrayOfNames(result);
                        console.log(res);
                        func();
                    },
                    error: function (error) {
                        console.log(error);
                        window.alert('Błąd w trakcie usuwaniaładowania danych do wykresu');
                    }
                }
            );

            function func() {
                for (let i = 0; i < res.length; i++) {
                    colorsBarBodyRepo.push("rgba(255, 0, 255, 0.4)");
                    colorsBarHoverRepo.push("rgba(255, 100, 0, 0.7)");
                    colorsBarHoverBorderRepo.push("rgba(0, 255, 100, 1)");
                }
                labels = res.map(a => formatLabel(a.eventName, 50));
                values = res.map(a => a.count);

                let MeSeData = {
                    labels: labels,
                    datasets: [
                        {
                            label: "Wejścia",
                            data: values,
                            backgroundColor: colorsBarBodyRepo,
                            hoverBackgroundColor: colorsBarHoverRepo,
                            hoverBorderColor: colorsBarHoverBorderRepo,
                            barPercentage: 0.75,
                            hoverBorderWidth: 2
                        }]
                };

                let ctx = document.getElementById("myBarChart");
                let myBarChart = new Chart(ctx, {
                    type: 'horizontalBar',
                    data: MeSeData,
                    options: {
                        scales: {
                            xAxes: [{
                                ticks: {
                                    min: 0
                                }
                            }],
                            yAxes: [{
                                stacked: true
                            }]
                        },
                        layout: {
                            padding: {
                                left: 50,
                                right: 0,
                                top: 0,
                                bottom: 0
                            }
                        }
                    }
                });
            }
        }

        function chartHandler2() {
            let res;
            let labels;
            let values;
            let colorsBarBodyRepo = [];
            let colorsBarHoverRepo = [];
            let colorsBarHoverBorderRepo = [];

            $.ajax({
                    url: "/api/view-stat/global/org",
                    type: "GET",
                    success: function (result) {
                        res = eventJsonToArrayOfNames(result);
                        console.log(res);
                        func();
                    },
                    error: function (error) {
                        console.log(error);
                        window.alert('Błąd w trakcie usuwaniaładowania danych do wykresu');
                    }
                }
            );

            function func() {
                for (let i = 0; i < res.length; i++) {
                    colorsBarBodyRepo.push("rgba(255, 0, 255, 0.4)");
                    colorsBarHoverRepo.push("rgba(255, 255, 0, 0.7)");
                    colorsBarHoverBorderRepo.push("rgba(0, 255, 100, 1)");
                }
                labels = res.map(a => formatLabel(a.organizerName, 50));
                values = res.map(a => a.count);

                let MeSeData = {
                    labels: labels,
                    datasets: [
                        {
                            label: "Wejścia",
                            data: values,
                            backgroundColor: colorsBarBodyRepo,
                            hoverBackgroundColor: colorsBarHoverRepo,
                            hoverBorderColor: colorsBarHoverBorderRepo,
                            barPercentage: 0.9,
                            hoverBorderWidth: 2
                        }]
                };

                let ctx = document.getElementById("myBarChart2");
                let myBarChart = new Chart(ctx, {
                    type: 'horizontalBar',
                    data: MeSeData,
                    options: {
                        scales: {
                            xAxes: [{
                                ticks: {
                                    min: 0
                                }
                            }],
                            yAxes: [{
                                stacked: true
                            }]
                        },
                        layout: {
                            padding: {
                                left: 50,
                                right: 0,
                                top: 0,
                                bottom: 0
                            }
                        }
                    }
                });
            }
        }

        function chartHandler3() {
            let res;
            let labels;
            let values;
            let colorsBarBodyRepo = [];
            let colorsBarHoverRepo = [];
            let colorsBarHoverBorderRepo = [];


            console.log(ff);

            $.ajax({
                    url: "/api/view-stat/period",
                    type: "POST",
                    data: ff,
                    dataType: "json",
                    contentType: "application/json",
                    success: function (result) {
                        res = eventJsonToArrayOfNames(result);
                        console.log(res);
                        func();
                    },
                    error: function (error) {
                        console.log(error);
                        window.alert('Błąd w trakcie ładowania danych do wykresu');
                    }
                }
            );

            function func() {
                for (let i = 0; i < res.length; i++) {
                    colorsBarBodyRepo.push("rgba(255, 0, 255, 0.4)");
                    colorsBarHoverRepo.push("rgba(255, 255, 0, 0.7)");
                    colorsBarHoverBorderRepo.push("rgba(0, 255, 100, 1)");
                }
                labels = res.map(a => formatLabel(a.eventName, 50));// ;
                values = res.map(a => a.count);

                let MeSeData = {
                    labels: labels,
                    datasets: [
                        {
                            label: "Wejścia",
                            data: values,
                            backgroundColor: colorsBarBodyRepo,
                            hoverBackgroundColor: colorsBarHoverRepo,
                            hoverBorderColor: colorsBarHoverBorderRepo,
                            barPercentage: 0.9,
                            hoverBorderWidth: 2
                        }]
                };

                let ctx = document.getElementById("myBarChart3");
                let myBarChart = new Chart(ctx, {
                    type: 'horizontalBar',
                    data: MeSeData,
                    options: {
                        scales: {
                            xAxes: [{
                                ticks: {
                                    min: 0
                                }
                            }],
                            yAxes: [{
                                stacked: true
                            }]
                        },
                        layout: {
                            padding: {
                                left: 50,
                                right: 0,
                                top: 0,
                                bottom: 0
                            }
                        }
                    }
                });
            }
        }

        function chartHandler4() {
            let res;
            let labels;
            let values;
            let colorsBarBodyRepo = [];
            let colorsBarHoverRepo = [];
            let colorsBarHoverBorderRepo = [];

            console.log(ff);

            $.ajax({
                    url: "/api/view-stat/period/org",
                    type: "POST",
                    data: ff,
                    dataType: "json",
                    contentType: "application/json",
                    success: function (result) {
                        res = eventJsonToArrayOfNames(result);
                        console.log(res);
                        func();
                    },
                    error: function (error) {
                        console.log(error);
                        window.alert('Błąd w trakcie ładowania danych do wykresu');
                    }
                }
            );

            function func() {
                for (let i = 0; i < res.length; i++) {
                    colorsBarBodyRepo.push("rgba(255, 0, 255, 0.4)");
                    colorsBarHoverRepo.push("rgba(255, 255, 0, 0.7)");
                    colorsBarHoverBorderRepo.push("rgba(0, 255, 100, 1)");
                }
                labels = res.map(a => formatLabel(a.organizerName, 50));
                values = res.map(a => a.count);

                let MeSeData = {
                    labels: labels,
                    datasets: [
                        {
                            label: "Wejścia",
                            data: values,
                            backgroundColor: colorsBarBodyRepo,
                            hoverBackgroundColor: colorsBarHoverRepo,
                            hoverBorderColor: colorsBarHoverBorderRepo,
                            barPercentage: 0.9,
                            hoverBorderWidth: 2
                        }]
                };

                let ctx = document.getElementById("myBarChart4");
                let myBarChart = new Chart(ctx, {
                    type: 'horizontalBar',
                    data: MeSeData,
                    options: {
                        scales: {
                            xAxes: [{
                                ticks: {
                                    min: 0
                                }
                            }],
                            yAxes: [{
                                stacked: true
                            }]
                        },
                        layout: {
                            padding: {
                                left: 50,
                                right: 0,
                                top: 0,
                                bottom: 0
                            }
                        }
                    }
                });
            }
        }

        function load1() {
            let d1 = document.getElementById("date1").value + " " + document.getElementById("time1").value;
            let d2 = document.getElementById("date2").value + " " + document.getElementById("time2").value;
            ff = JSON.stringify({date1: d1, date2: d2});
            console.log(ff);
            chartHandler3();
        }

        function load2() {
            let d3 = document.getElementById("date3").value + " " + document.getElementById("time3").value;
            let d4 = document.getElementById("date4").value + " " + document.getElementById("time4").value;
            ff = JSON.stringify({date1: d3, date2: d4});
            console.log(ff);
            chartHandler4();
        }

        function openTab(evt, tabName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
        }
    </script>

</div>

<#include "footerWithScripts.ftlh">
</body>
</html>