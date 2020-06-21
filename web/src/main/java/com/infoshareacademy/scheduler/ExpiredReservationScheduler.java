package com.infoshareacademy.scheduler;

import com.infoshareacademy.service.ReservationService;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

@Singleton
@Startup
public class ExpiredReservationScheduler {

    private static final Logger STDLOG = Logger.getLogger(ExpiredReservationScheduler.class.getName());

    @Inject
    ReservationService reservationService;

    @Schedule(hour = "*", minute = "*", second = "0", info = "Each minute")
    public void checkReservations() {
        reservationService.deleteExpiredScheduler();
        STDLOG.info("Checking expired reservations routine finished");
    }
}
