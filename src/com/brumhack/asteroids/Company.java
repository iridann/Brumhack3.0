package com.brumhack.asteroids;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by janospotecki on 24/10/15.
 */


public class Company {
    private class Year {
        private int[] months = new int[12];
        private String name;
        public Year(String name){
            this.name = name;
        }

        public void addValue(int month, int value){
            if(month >= this.months.length())
                throw new RuntimeException("month not in this year");
            this.months[month] = value;
        }

        public int getValue(int month){
            if(month >= this.months.size())
                throw new RuntimeException("month not in this year");

            return this.months.get(month);
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

    public addYear(Year year){
        this.years.put()
    }


}
