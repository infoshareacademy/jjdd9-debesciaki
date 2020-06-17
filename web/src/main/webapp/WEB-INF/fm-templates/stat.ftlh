<!DOCTYPE html>
<html lang="pl-PL">
<#include "headSection.ftlh">
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
    <h2>Wydarzenia</h2>
    <div class="container">
        <canvas id="myBarChart" width="100" height="55"></canvas>
    </div>
    <h2>Organizatorzy</h2>
    <div class="container">
        <canvas id="myBarChart2" width="100" height="40"></canvas>
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
                labels = res.map(a => formatLabel(a.eventName, 40));
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
                labels = res.map(a => formatLabel(a.organizerName, 40));
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
    </script>

</div>

<#include "footerWithScripts.ftlh">
</body>
</html>