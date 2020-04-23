package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        STDOUT.info("{}Czerwony{}Zielony{}Niebieski{}Żółty{}\n", ConsoleColor.RED, ConsoleColor.GREEN, ConsoleColor.BLUE, ConsoleColor.YELLOW, ConsoleColor.RESET);
    }

    public void currentEvents() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");     //("yyyy-MM-dd'T'HH:mm:ssZ");
        STDOUT.info("{}\n", LocalDateTime.now().format(formatter));
        DateTimeFormatter eventFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        //ZonedDateTime.parse("2020-04-21T17:00:00+0200",formatter);// 0 19
        String actT = LocalDateTime.now().format(formatter);
        //sprawdź czy null
        Optional<Integer> compQty = inputInteger("Ile nadchodzących wydarzeń chcesz zobaczyć łącznie? --> ");
        Optional<Integer> pageMaxElements = inputInteger("Ile nadchodzących wydarzeń chcesz zobaczyć na jednej stronie? --> ");
        Integer qty = compQty.get();
        Integer elemPerPage = pageMaxElements.get();
        STDOUT.info("Number is: {} \n", qty);


        Map<Integer, Event> eventMap = selectedMap(qty);
        List<Event> eventList =selectedList(qty);
        displayPages(qty,elemPerPage,eventList);

        /*for (Event e : eventMap.values()) {
            consoleEventScheme(e);
        }

         */

        STDOUT.info("size of map {}\nsize of a repository  {}\n", eventMap.size(), EventRepository.getAllEvents().size());


    }

    public Optional<Integer> inputInteger(String subject) {
        Integer qty = null;
        Optional<Integer> opt = Optional.ofNullable(qty);
        do {
            STDOUT.info("{}",subject);
            try {
                Scanner scanner = new Scanner(System.in);
                opt = Optional.ofNullable(qty = scanner.nextInt());
            } catch (InputMismatchException e) {
                STDOUT.info("Źle wprowadzone dane, spróbuj ponownie! msg: {}\n", e.getMessage());
            }
        } while (opt.isEmpty());
        return opt;
    }

    public Map<Integer, Event> selectedMap(int qty) {
        //Selective filling
        Map<Integer, Event> eventMap = new HashMap<>();
        for (Event e : EventRepository.getAllEvents()) {
            if (eventMap.size() < qty) {
                if (compareDateStrings(e.getEndDate())) eventMap.put(e.getId(), e);
            }
        }
        return eventMap;
    }
    public List<Event> selectedList(int qty) {
        //Selective filling
        List< Event> eventList = new ArrayList<>();
        for (Event e : EventRepository.getAllEvents()) {
            if (eventList.size() < qty) {
                if (compareDateStrings(e.getEndDate())) eventList.add(e);
            }
        }
        return eventList;
    }

    public boolean compareDateStrings(String eventT) {
        if (Integer.parseInt(eventT.substring(0, 4)) == LocalDateTime.now().getYear()) {
            if (Integer.parseInt(eventT.substring(5, 7)) == (LocalDateTime.now().getMonthValue())) {
                if (Integer.parseInt(eventT.substring(8, 10)) == LocalDateTime.now().getDayOfMonth()) {
                    if (Integer.parseInt(eventT.substring(11, 13)) == LocalDateTime.now().getHour()) {
                        if (Integer.parseInt(eventT.substring(14, 16)) == LocalDateTime.now().getMinute()) {
                            if (Integer.parseInt(eventT.substring(17, 19)) > LocalDateTime.now().getSecond()) {
                                return true;
                            } else return false;
                        } else if (Integer.parseInt(eventT.substring(14, 16)) > LocalDateTime.now().getMinute()) {
                            return true;
                        } else return false;
                    } else if (Integer.parseInt(eventT.substring(11, 13)) > LocalDateTime.now().getHour()) {
                        return true;
                    } else return false;
                } else if (Integer.parseInt(eventT.substring(8, 10)) > LocalDateTime.now().getDayOfMonth()) {
                    return true;
                } else return false;
            } else if (Integer.parseInt(eventT.substring(5, 7)) > LocalDateTime.now().getMonthValue()) {
                return true;
            } else return false;
        } else if (Integer.parseInt(eventT.substring(0, 4)) > LocalDateTime.now().getYear()) {
            return true;
        } else return false;
    }

    public void displayPages(Integer qty, Integer elemPerPage,List< Event> eventList ){
        Optional<Integer> decision =null;
        double pageCountd = Math.ceil((double)qty/elemPerPage);
        Integer pageCount = (int)pageCountd;
        int limU=0, limD=elemPerPage,actual=1;
        do {
           for (int i=limU;i<limD;i++){
               if (i<eventList.size()){
               Event e=eventList.get(i);
               consoleEventScheme(e);}
           }
            decision =inputInteger("0-wyjdź\n1-poprzednia\n2-następna ");
           int dec = decision.get();
           if (actual>1&&dec==1){
               actual--;
               limU-=elemPerPage;
               limD-=elemPerPage;
           }else if (actual<pageCount&&dec==2){
               actual++;
               limU+=elemPerPage;
               limD+=elemPerPage;
           }else if (dec!=1&&dec!=2){
               break;
           }
        }while (decision.get()==1 || decision.get()==2);
    }

    //Configuration for displaying event in console
    public void consoleEventScheme(Event e){
        Place p = e.getPlace();
        STDOUT.info("Name: {}{}{}\nPlace: {}{}{} \nEnd date: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getName(), ConsoleColor.RESET,
                ConsoleColor.BLUE, p.getName(), ConsoleColor.RESET,
                ConsoleColor.CYAN_BACKGROUND, e.getEndDate(), ConsoleColor.RESET);
    }

    //this.year =Integer.parseInt(Date.substring(0,4));
    //  this.month=Integer.parseInt(Date.substring(5,7));
    // this.day =Integer.parseInt(Date.substring(8,10));
    // this.hour =Integer.parseInt(Date.substring(11,13));
    // this.minute =Integer.parseInt(Date.substring(14,16));
    //this.seconds=Integer.parseInt(Date.substring(17,19));


}
