package com.infoshareacademy.display;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public void run(){
        STDOUT.info("{}Czerwony{}Zielony{}Niebieski{}Żółty{}\n",ConsoleColor.RED,ConsoleColor.GREEN,ConsoleColor.BLUE,ConsoleColor.YELLOW,ConsoleColor.RESET);
    }
}
