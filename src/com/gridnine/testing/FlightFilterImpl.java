package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImpl implements FlightFilter{


    // Метод для получения списка полетов без вылетов до текущего момента времени
    @Override
    public List<Flight> flightsBeforeCurrentTime(List<Flight> flights) {
        return flights.stream().filter(flight -> flight.getSegments().stream().anyMatch(segment -> !segment.getDepartureDate().isBefore(LocalDateTime.now()))).toList();
    }

    //Метод для получения списка полетов без неправильных сегментов
    @Override
    public List<Flight> flightsWithWrongSegments(List<Flight> flights) {
        return flights.stream().filter(flight -> flight.getSegments().stream().anyMatch(segment -> !segment.getDepartureDate().isAfter(segment.getArrivalDate()))).toList();
    }

    //Метод для получения списка полетов со временем на земле не более x часов
    @Override
    public List<Flight> flightWithLessGroundTime(List<Flight> flights, int hours) {
        List<Flight> filteredFlights = new ArrayList<>();
        LocalTime groundTime;                                 //всего времени на земле за один полет
        LocalTime limit = LocalTime.of(hours,0);       //лимит часов на земле между сегментами
        for( int i = 0 ; i < flights.size() ; i++ ){
            List<Segment> segments = new ArrayList<>(flights.get(i).getSegments());
            if(segments.size() > 1){
                groundTime = LocalTime.of(0,0);
                for( int j = 0 ; j < segments.size() - 1 ; j++){
                    groundTime = groundTime.plusHours(segments.get(j+1).getDepartureDate().getHour() - segments.get(j).getArrivalDate().getHour());
                    groundTime = groundTime.plusMinutes(segments.get(j).getArrivalDate().getMinute() - segments.get(j+1).getDepartureDate().getMinute());
                }
                if(!groundTime.isAfter(limit)){
                    filteredFlights.add(flights.get(i));
                }
            }else {
                filteredFlights.add(flights.get(i));
            }
        }
        return filteredFlights;
    }
}
