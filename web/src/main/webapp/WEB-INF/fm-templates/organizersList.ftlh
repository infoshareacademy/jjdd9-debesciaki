<div class="container">
    <#include "sneakPeak.ftlh">
    <div class="jumbotron ">
        <h3 class="display-4 text-center">Lista organizatorów</h3><br>
        <#include "searchInput.ftlh"><br>
        <table id="dtBasicExample" class="table table-striped table-bordered table-hover table-sm bg-white align-content-center" cellspacing="0" width="70%">
            <thead>
            <tr class="bg-dark text-white">
                <th class="th-sm text-center" width="100%">Nazwa</th>
            </tr>
            </thead>
            <tbody>
            <#list organizers as organizer>
                <tr>
                    <td class="text-primary font-weight-bold"><a href="/show-events?action=search&page=1&phrase=${organizer.designation}">${organizer.designation}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <#if numberOfOrganizers gt 20>
            <#include "navigationUnderTable.ftlh">
        </#if>
    </div>
</div>