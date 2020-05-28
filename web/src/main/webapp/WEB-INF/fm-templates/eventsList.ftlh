<div class="container">
    <div class="jumbotron ">
        <h3 class="display-4 text-center">Lista nadchodzących wydarzeń</h3><br>
        <table id="dtBasicExample" class="table table-striped table-bordered table-hover table-sm bg-white" cellspacing="0">
            <thead>
            <tr class="bg-dark text-white">
                <th class="th-sm text-center" width="5%">Numer</th>
                <th class="th-sm text-center" width="55%">Nazwa</th>
                <th class="th-sm text-center" width="20%">Data rozpoczęcia</th>
                <th class="th-sm text-center" width="20%">Data zakończenia</th>
            </tr>
            </thead>
            <tbody>
            <#list events as event>
                <tr>
                    <td class="text-center">${event.id}</td>
                    <td class="text-center">${event.name}</td>
                    <td class="text-center">${event.startDate}</td>
                    <td class="text-center">${event.endDate}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <#if numberOfEvents gt 20>
        <#include "eventsNavigation.ftlh">
        </#if>
    </div>
</div>