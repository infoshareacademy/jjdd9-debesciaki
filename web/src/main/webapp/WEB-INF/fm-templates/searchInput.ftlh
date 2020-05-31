<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a>
                        <#noautoesc>
                            <form method="get" action="/show-events">
                                <button class="btn btn-primary"
                                        name="action"
                                        type="submit"
                                        value="search"
                                        style="float: left">
                                    Szukaj
                                </button>
                                <input type="hidden" name="page" value="1"/>
                                <div style="overflow: hidden; padding-right: .5em;">
                                    <input type="text" name="phrase" style="width: 100%;"/>
                                </div>
                            </form>
                        </#noautoesc>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
