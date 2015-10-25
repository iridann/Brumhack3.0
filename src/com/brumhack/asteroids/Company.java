package com.brumhack.asteroids;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by janospotecki on 24/10/15.
 */


public class Company {


    private class Year {
        private int[] months;
        private int name;

        private Year(int name){
            this.name = name;
        }
        public int getName(){
            return this.name;
        }

        public void addValue(int month, int value){
            month--;
            if(month > 12 || month < 0)
                throw new RuntimeException("month not in this year");
            this.months[month] = value;
        }

        public int getValue(int month){
            month--;
            if(month > 11 || month < 0)
                throw new RuntimeException("month not in this year");
            return this.months[month];
        }


    }

    private Map years = new HashMap<>();
    private String name;

    public Company(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addYear(Year year){
        this.years.put(year.getName(), year);
    }

    public boolean hasYear(int year){
        if( this.years.get(year) == null)
            return false;
        return true;
    }

    public void insertMonth(int month, int value, int year){
        ( (Year)this.years.get(year) ).addValue(month, value);
    }








}
