<#noautoesc>
    <form method="get" action="/show-events">
        <button class="btn btn-primary"
                name="action"
                type="submit"
                value="search"
                style="float: right"
                required minlength="3">
            Szukaj
        </button>
        <input type="hidden" name="page" value="1"/>
        <div style="overflow: hidden; padding-right: .5em;">
            <input type="text" name="phrase" required minlength="3" style="width: 100%;"/>
        </div>
    </form>
</#noautoesc>

