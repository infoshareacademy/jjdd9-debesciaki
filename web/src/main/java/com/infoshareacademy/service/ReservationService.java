package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.Reservation;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.mail.EventFinishedMail;
import com.infoshareacademy.mail.MailService;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.ReservationDao;
import com.infoshareacademy.repository.UserDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

@Stateless
public class ReservationService {

    private static final Logger STDLOG = LoggerFactory.getLogger(ReservationService.class.getName());

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private EventDao eventDao;

    @Inject
    private UserDao userDao;

    @Inject
    private MailService mailService;


    public void requestReservation(Long eventId, String mail) {

        Optional<Event> eventOptional = eventDao.findById(eventId);
        Optional<User> userOptional = userDao.findByEmail(mail);

        if (eventOptional.isPresent() && userOptional.isPresent()) {
            Reservation reservation = new Reservation();

            reservation.setEvent(eventOptional.get());
            reservation.setUser(userOptional.get());
            reservation.setExpirationDate(LocalDateTime.now().plusMinutes(15L));
            reservation.setConfirmed(Boolean.FALSE);

            String token = RandomStringUtils.randomAlphabetic(10);
            reservation.setToken(token);

            reservationDao.save(reservation);
            mailService.sendEmail(new EventFinishedMail(token, token), mail);
        } else {
            STDLOG.error("Failed at finding event that is supposed to be reserved, probabble wrong ID passed or problem with finding user in database");
        }
    }

    public String consumeToken(String token){
        Optional<Reservation> optionalReservation = reservationDao.findByToken(token);
        if (optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();

            if (LocalDateTime.now().isAfter(reservation.getExpirationDate())){
                reservationDao.delete(reservation);
                return "Link jest przeterminowany, możesz spróbować dokonać rezerwacji ponownie :)";
            }

            reservation.setConfirmed(Boolean.TRUE);
            reservationDao.update(reservation);

            return "Dokonano potwierdzenia rezerwacji";
        }else{
            return "Link potwierdzający jest niepoprawny";
        }
    }
}
