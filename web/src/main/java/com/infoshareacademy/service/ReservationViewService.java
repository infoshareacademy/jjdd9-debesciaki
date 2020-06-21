package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.Reservation;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.view.EventReservationView;
import com.infoshareacademy.mail.MailService;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.ReservationDao;
import com.infoshareacademy.repository.UserDao;
import com.infoshareacademy.service.stat.TicketStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class ReservationViewService {

    private static final Logger STDLOG = LoggerFactory.getLogger(ReservationViewService.class.getName());

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Inject
    private ReservationDao reservationDao;

    @Inject
    TicketStatService ticketStatService;

    @Inject
    private EventDao eventDao;

    @Inject
    private UserDao userDao;

    @Inject
    private MailService mailService;

    public List<EventReservationView> prepareReservedList(int firstResult, String mail) {
        List<EventReservationView> eventReservationViewList = new ArrayList<>();

        int i = 0;
        for (Reservation reservation : getReservationList(mail)) {
            if (i >= firstResult) {
                Event event = reservation.getEvent();
                EventReservationView e = mapEventAndReservationToView(event, reservation);
                eventReservationViewList.add(e);
            }
            i++;
        }
        return eventReservationViewList;
    }


    public Integer getReservationCount(String mail) {
        return getReservationList(mail).size();
    }

    private EventReservationView mapEventAndReservationToView(Event event, Reservation reservation) {
        EventReservationView eventReservationView = new EventReservationView();

        eventReservationView.setId(event.getId());

        eventReservationView.setName(event.getName());
        eventReservationView.setOrganizerName((Optional.ofNullable(event.getOrganizer().getDesignation()).isPresent()) ? event.getOrganizer().getDesignation() : "Brak informacji");

        eventReservationView.setStartDate(event.getStartDate().format(formatter));
        eventReservationView.setEndDate(event.getEndDate().format(formatter));

        eventReservationView.setToken(reservation.getToken());

        if (reservation.getConfirmed()) {
            eventReservationView.setIsConfirmed("Potwierdzono");
        } else {
            eventReservationView.setIsConfirmed("Oczekuje na potwierdzenie");
        }

        if (reservation.getFull()) {
            eventReservationView.setReservationType("Normalna");
        } else {
            eventReservationView.setReservationType("Ulgowa");
        }
        return eventReservationView;
    }

    private List<Reservation> getReservationList(String mail) {
        Optional<User> userOptional = userDao.findByEmail(mail);
        List<Reservation> reservationList = new ArrayList<>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            reservationList = reservationDao.findByUser(user);
        }

        return reservationList;
    }
}
