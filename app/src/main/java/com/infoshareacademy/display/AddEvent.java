package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.repository.JSONFileChanger;
import com.infoshareacademy.validator.Validator;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class AddEvent {
    AddEvent() {
        JSONFileChanger writer = new JSONFileChanger();
        Validator v = new Validator();
        Event newEvent = new Event();
        EditEvent editEntity = new EditEvent();
        cleanConsole();
        editEntity.addId(newEvent);
        editEntity.editName(newEvent);
        editEntity.editStartDate(newEvent);
        editEntity.editEndDate(newEvent);
        editEntity.editPlace(newEvent);
        editEntity.editOrganizer(newEvent);
        editEntity.editShortDesc(newEvent);
        editEntity.editLongDesc(newEvent);
        editEntity.editUrl(newEvent);
        editEntity.editTicket(newEvent);
        writer.addEvent(newEvent);
    }
}
