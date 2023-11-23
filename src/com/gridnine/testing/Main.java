package com.gridnine.testing;

public class Main {
    public static void main(String[] args) {
        FlightFilterImpl test = new FlightFilterImpl();
        System.out.println("Список полетов без вылетов до текущего момента времени:");
        for(Flight f : test.flightsBeforeCurrentTime(FlightBuilder.createFlights())){
            System.out.println(f.toString());
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Список полетов без неправильных сегментов:");
        for(Flight f : test.flightsWithWrongSegments(FlightBuilder.createFlights())){
            System.out.println(f.toString());
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Список полетов с временем на земле не более 2-х часов:");
        for(Flight f : test.flightWithLessGroundTime(FlightBuilder.createFlights(),2)){
            System.out.println(f.toString());
        }
    }
}