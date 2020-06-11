<#if role == "USER" >
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Panel użytkownika
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/favourites">Lista ulubionych</a>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/logoutGoogle">Wyloguj się</a>
    </li>
<#elseif role == "ADMIN" || role == "SUPER_ADMIN">
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Panel administratora
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/upload.html">Załaduj bazę</a>
            <a class="dropdown-item" href="/users-list">Lista użytkowników</a>
            <a class="dropdown-item" href="#">Statystyki</a>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/logoutGoogle">Wyloguj się</a>
    </li>
<#else>
    <li class="nav-item">
        <a class="nav-link" href="/login">Zaloguj się</a>
    </li>
</#if>