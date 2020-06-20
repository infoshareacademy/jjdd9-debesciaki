<#if role == "USER" >
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Panel użytkownika
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/show-favourites?page=1">Lista ulubionych</a>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/logoutGoogle">Wyloguj się</a>
    </li>
<#elseif role == "ADMIN" || role == "SUPER_ADMIN">
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Panel użytkownika
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/show-favourites?page=1">Lista ulubionych</a>
        </div>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Panel administratora
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/upload-rest">Załaduj bazę</a>
            <a class="dropdown-item" href="/users-list">Lista użytkowników</a>
            <a class="dropdown-item" href="/stat">Statystyki</a>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/logoutGoogle">Wyloguj się</a>
    </li>
<#else>
    <li class="nav-item">
        <a class="nav-link" href="/loginGoogle">Zaloguj się</a>
    </li>
</#if>