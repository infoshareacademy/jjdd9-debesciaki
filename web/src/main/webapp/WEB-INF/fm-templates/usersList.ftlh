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
                <li class="nav-item">
                    <a class="nav-link" href="/">Strona główna</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/show-events?action=showAll&page=1">Wydarzenia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show-organizers?page=1">Organizatorzy</a>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Panel administratora<span class="sr-only">(current)</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/upload-rest">Załaduj bazę</a>
                        <a class="dropdown-item" href="/users-list">Lista użytkowników</a>
                        <a class="dropdown-item" href="#">Statystyki</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logoutGoogle">Wyloguj się</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br><br>
<div class="container">
    <div class="jumbotron ">
        <h3 class="display-4 text-center">Lista zarejestrowanych użytkowników</h3><br>

        <table id="dtBasicExample"
               class="table table-striped table-bordered table-hover table-sm bg-white align-content-center"
               cellspacing="0" width="70%">
            <thead>
            <tr class="bg-dark text-white">
                <th class="th-sm text-center" width="50%">Email</th>
                <th class="th-sm text-center" width="50%">Rola użytkownika</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <form method="post" action="/users-list">
                        <td class="text-primary font-weight-bold">${user.email}</td>
                        <td class="text-primary font-weight-bold">
                            <#if user.role == "SUPER_ADMIN"> Główny administrator aplikacji
                            <#elseif user.role == "ADMIN">
                                <#if user.email == email>
                                    Administrator
                                <#else>
                                    <input type="hidden" name="email" value="${user.email}"/>
                                    <select class="form-control" name="role"
                                            onchange='if(this.value != "ADMIN") { this.form.submit(); }'>
                                        <option value="ADMIN" name="ADMIN">Administrator</option>
                                        <option value="USER" name="USER">Użytkownik</option>
                                    </select>
                                </#if>
                            <#elseif user.role == "USER">
                                <input type="hidden" name="email" value="${user.email}"/>
                                <select class="form-control" name="role"
                                        onchange='if(this.value != "USER") { this.form.submit(); }'>
                                    <option value="USER" name="USER">Użytkownik</option>
                                    <option value="ADMIN" name="ADMIN">Administrator</option>
                                </select>
                            </#if></td>
                    </form>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
<#include "footerWithScripts.ftlh">
</body>
</html>