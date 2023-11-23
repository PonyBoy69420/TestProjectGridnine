package com.gridnine.testing;

import java.util.List;

public interface FlightFilter {
    List<Flight> flightsBeforeCurrentTime(List<Flight> flights);
    List<Flight> flightsWithWrongSegments(List<Flight> flights);
    List<Flight> flightWithLessGroundTime(List<Flight> flights, int hours);
}
