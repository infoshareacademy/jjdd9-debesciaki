<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a class="page-link" href="/show-events?page=1" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Poprzednia</span>
            </a>
        </li>

        <#if actPage == 1>
            <li class="page-item active"><a class="page-link" href=/show-events?page=${actPage}">${actPage}</a></li>
            <li class="page-item"><a class="page-link" href="/show-events?page=${actPage+1}">${actPage+1}</a></li>
        <#elseif actPage != numberOfPages>
            <li class="page-item"><a class="page-link" href="/show-events?page=${actPage-1}">${actPage-1}</a></li>
            <li class="page-item active"><a class="page-link" href=/show-events?page=${actPage}">${actPage}</a></li>
            <li class="page-item"><a class="page-link" href="/show-events?page=${actPage+1}">${actPage+1}</a></li>
        <#else>
            <li class="page-item"><a class="page-link" href="/show-events?page=${actPage-1}">${actPage-1}</a></li>
            <li class="page-item active"><a class="page-link" href=/show-events?page=${actPage}">${actPage}</a></li>
        </#if>

        <li class="page-item">
            <a class="page-link" href="/show-events?page=${numberOfPages}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Następna</span>
            </a>
        </li>
    </ul>
</nav>