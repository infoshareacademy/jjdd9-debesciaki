<div class="container">
    <div class="jumbotron ">
        <h3 class="display-4 text-center">Rezerwacje</h3>
        <#if (events?size > 0) >
            <table id="dtBasicExample" class="table table-striped table-bordered table-hover table-sm bg-white"
                   cellspacing="0">
                <thead>
                <tr class="bg-dark text-white">
                    <th class="th-sm text-center" width="30%">Nazwa</th>
                    <th class="th-sm text-center" width="20%">Organizator</th>
                    <th class="th-sm text-center" width="15%">Data rozpoczęcia</th>
                    <th class="th-sm text-center" width="15%">Data zakończenia</th>
                    <th class="th-sm text-center" width="5%">Usuń rezerwacje</th>
                    <th class="th-sm text-center" width="10%">Typ rezerwacji</th>
                    <th class="th-sm text-center" width="5%">Potwierdzenie</th>
                </tr>
                </thead>
                <tbody>
                <#list events as event>
                    <tr>
                        <td class="text-primary font-weight-bold"><a
                                    href="/show-one-event?event=${event.id}">${event.name}</a></td>
                        <td class="text-center">${event.organizerName}</td>
                        <td class="text-center">${event.startDate}</td>
                        <td class="text-center">${event.endDate}</td>
                        <td class="text-center"><#include "reservationRemoveButton.ftlh"></td>
                        <td class="text-center">${event.reservationType}</td>
                        <td class="text-center">${event.isConfirmed}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <#if numberOfEvents gt 20>
                <#include "navigationUnderTable.ftlh">
            </#if>
        <#else>
            <h4 class="display-4 text-center">Brak rezerwacji, czas coś zarezerwować.</h4>
        </#if>
    </div>
</div>