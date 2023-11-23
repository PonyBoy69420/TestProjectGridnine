package com.gridnine.testing;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


class FlightFilterImplTest {
    private FlightFilterImpl flightFilter = new FlightFilterImpl();


    //Тест метода для получения списка полетов без вылетов до текущего момента времени
    @org.junit.jupiter.api.Test
    void flightsBeforeCurrentTime() {
        List<Flight> expectations = new ArrayList<>();
        expectations.add(FlightBuilder.createFlights().get(0));
        expectations.add(FlightBuilder.createFlights().get(1));
        expectations.add(FlightBuilder.createFlights().get(3));
        expectations.add(FlightBuilder.createFlights().get(4));
        expectations.add(FlightBuilder.createFlights().get(5));
        List<Flight> actual = new ArrayList<>(flightFilter.flightsBeforeCurrentTime(FlightBuilder.createFlights()));
        Assert.assertEquals(expectations.toString(),actual.toString());
    }

    //Тест метода для получения списка полетов без неправильных сегментов
    @org.junit.jupiter.api.Test
    void flightsWithWrongSegments() {
        List<Flight> expectations = new ArrayList<>();
        expectations.add(FlightBuilder.createFlights().get(0));
        expectations.add(FlightBuilder.createFlights().get(1));
        expectations.add(FlightBuilder.createFlights().get(2));
        expectations.add(FlightBuilder.createFlights().get(4));
        expectations.add(FlightBuilder.createFlights().get(5));
        List<Flight> actual = new ArrayList<>(flightFilter.flightsWithWrongSegments(FlightBuilder.createFlights()));
        Assert.assertEquals(expectations.toString(),actual.toString());
    }

    
    //Тест метода для получения списка полетов со временем на земле не более x часов
    @org.junit.jupiter.api.Test
    void flightWithLessGroundTime() {
        List<Flight> expectations = new ArrayList<>();
        expectations.add(FlightBuilder.createFlights().get(0));
        expectations.add(FlightBuilder.createFlights().get(1));
        expectations.add(FlightBuilder.createFlights().get(2));
        expectations.add(FlightBuilder.createFlights().get(3));
        List<Flight> actual = new ArrayList<>(flightFilter.flightWithLessGroundTime(FlightBuilder.createFlights(),2));
        Assert.assertEquals(expectations.toString(),actual.toString());
    }
}