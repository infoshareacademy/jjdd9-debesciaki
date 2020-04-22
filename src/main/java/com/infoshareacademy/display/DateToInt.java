package com.infoshareacademy.display;

import java.util.Date;

public class DateToInt {
   private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int seconds;

    public DateToInt() {
    }

    public void convert(String Date){
        this.year =Integer.parseInt(Date.substring(0,4));
        this.month=Integer.parseInt(Date.substring(5,7));
        this.day =Integer.parseInt(Date.substring(8,10));
        this.hour =Integer.parseInt(Date.substring(11,13));
        this.minute =Integer.parseInt(Date.substring(14,16));
        this.seconds=Integer.parseInt(Date.substring(17,19));
    }
    public int dayTimeSec(){
      return  this.seconds+(this.minute*60)+(this.hour*3600);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
