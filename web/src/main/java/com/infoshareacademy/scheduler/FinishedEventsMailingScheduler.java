package com.infoshareacademy.scheduler;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Logger;

@Singleton
@Startup
public class FinishedEventsMailingScheduler {

    private static final Logger STDLOG = Logger.getLogger(FinishedEventsMailingScheduler.class.getName());


    @Schedule(hour = "*", minute = "*", second = "0", info = "Each minute")
    public void fixedRate() {
        STDLOG.info("TEST\n\n\n\n\n\n\n\n\n\n\nTEST ");

    }
}
