package com.solvd.laba;

import com.solvd.laba.myenums.SeatType;
import com.solvd.laba.myinterfaces.UniqueIdInterface;


public final class Flight implements UniqueIdInterface {

    private static enum PlaneType {
        A380(16, 4, 90, 
        6, 410, 10, 518, "Airbus", "A380"),
        A320(14, 2, 20, 
        4, 112, 7, 146, "Airbus", "A320"),
        B737(4, 2, 12, 
        4, 102, 6, 118, "Boeing", "737");

        final int SEATS_IN_FIRST;
        final int SEATS_IN_BUSINESS;
        final int SEATS_IN_ECON;
        final int TOTAL_SEATS;
        final int NUM_COLUMNS_FIRST;
        final int NUM_COLUMNS_BUSINESS;
        final int NUM_COLUMNS_ECON;

        final String COMPANY;
        final String CLASSIFICATION;

        PlaneType(int seatsInFirst, int numColumnFirst, int seatsInBusiness, int numColumnBus, 
        int seatsInEcon, int numColumnEcon, int totalSeats, String company, String classification) {
            this.SEATS_IN_FIRST = seatsInFirst;
            this.SEATS_IN_BUSINESS = seatsInBusiness;
            this.SEATS_IN_ECON = seatsInEcon;
            this.TOTAL_SEATS = totalSeats;
            this.COMPANY = company;
            this.CLASSIFICATION = classification;
            this.NUM_COLUMNS_FIRST = numColumnFirst;
            this.NUM_COLUMNS_BUSINESS = numColumnBus;
            this.NUM_COLUMNS_ECON = numColumnEcon;
        }
    }

    private PlaneType planeType;
    public static int numFlights = 0;
    private String flightNum, departFrom, arriveTo;
    private AirplaneBase plane;
    private Passenger[] passengers;
    private Seat[] firstClassSeats, businessClassSeats, economyClassSeats;  
    private int seatsAvailable, seatsInFirstCount = 0, seatsInBusinessCount = 0, seatsInEconCount = 0, numPassengers = 0, iD = 0;
    

    //Flight Class constructor 
    //assigns most fields with information dependent on each plane type
    //allocates memory for Passenger and Seat arrays
    //populates Seat array with seat objects
    public Flight(AirplaneBase myPlane, String departFrom, String arriveTo) {
        this.plane = myPlane;
        this.setPlaneType(myPlane);
        this.seatsAvailable = planeType.TOTAL_SEATS;
        this.flightNum = planeType.CLASSIFICATION + plane.getId();
        this.departFrom = departFrom;
        this.arriveTo = arriveTo;
        this.passengers = new Passenger[planeType.TOTAL_SEATS];
        this.populateSeats();
        this.iD = ++numFlights;
    }

    //sets enum PlaneType depending on Object instance
    private final void setPlaneType(AirplaneBase plane) {
        if (plane instanceof AirbusA380) {
            planeType = PlaneType.A380; 
        } else if (plane instanceof AirbusA320) {
            planeType = PlaneType.A320;
        } else if (plane instanceof Boeing737) {
            planeType = PlaneType.B737;
        }
    } 

    //populates Seat array with seats based on rows and columns 
    //ex: 4 columns means seats will have letters A, B, C, or D
    //rows are seat numbers
    //so row 1 with 4 columns would have seats 1A, 1B, 1C, and 1D
    private final void populateSeats() {
        firstClassSeats = new Seat[planeType.SEATS_IN_FIRST];
        businessClassSeats = new Seat[planeType.SEATS_IN_BUSINESS];
        economyClassSeats = new Seat[planeType.SEATS_IN_ECON];
        int first = planeType.SEATS_IN_FIRST;
        int totalRowFirst = (first / planeType.NUM_COLUMNS_FIRST);
        int bus = planeType.SEATS_IN_BUSINESS;
        int totalRowBus = (bus / planeType.NUM_COLUMNS_BUSINESS);
        int econ = planeType.SEATS_IN_ECON;
        int totalRowEcon = (econ / planeType.NUM_COLUMNS_ECON);

        for (int row = 1; row <= totalRowFirst; row++) {
            char seatLetter = 'A';
            for (int i = 0; i < planeType.NUM_COLUMNS_FIRST; i++) {
                Seat seat = new Seat(row, seatLetter);
                for (int j = 0; j < firstClassSeats.length; j++) {
                    if (firstClassSeats[j] == null) {
                        firstClassSeats[j] = seat;
                        break;
                    }
                }
                ++seatLetter; 
            }
        }

        for (int row = totalRowFirst + 1; row <= totalRowFirst + totalRowBus; row++) {
            char seatLetter = 'A';
            for (int i = 0; i < planeType.NUM_COLUMNS_BUSINESS; i++) {
                Seat seat = new Seat(row, seatLetter);
                for (int j = 0; j < businessClassSeats.length; j++) {
                    if (businessClassSeats[j] == null) {
                        businessClassSeats[j] = seat;
                        break;
                    }
                }
                ++seatLetter; 
            }
        }

        for (int row = totalRowFirst + totalRowBus + 1; row <= totalRowFirst + totalRowBus + totalRowEcon; row++) {
            char seatLetter = 'A';
            for (int i = 0; i < planeType.NUM_COLUMNS_ECON; i++) {
                Seat seat = new Seat(row, seatLetter);
                for (int j = 0; j < economyClassSeats.length; j++) {
                    if (economyClassSeats[j] == null) {
                        economyClassSeats[j] = seat;
                        break;
                    }
                }
                ++seatLetter; 
            }
        }
        
    }

    //returns available seats on flight inclusive
    public final boolean getSeatsAvailable() {
        return seatsAvailable > 0;
    }

    //returns available seats on flight for specific seatType
    public final boolean getSeatsAvailable(SeatType seatType) {
        switch (seatType) {
            case FIRST_CLASS:
                return seatsInFirstCount < planeType.SEATS_IN_FIRST;
            case BUSINESS_CLASS:
                return seatsInBusinessCount < planeType.SEATS_IN_BUSINESS;
            case ECONOMY_CLASS:
                return seatsInEconCount < planeType.SEATS_IN_ECON;
            default:
                break;
        }
        return false;
    }

    //returns flight ID#
    @Override
    public final int getId() {
        return iD;
    }
 
    //adds a passenger to a flight and assigns a seat number based on requested class
    //then the passenger is added to the array of passengers
    public final boolean bookSeat(Passenger person, SeatType seatType) throws DoubleBookException {
        for (int i = 0; i < passengers.length; i++) {
            if (person.equals(passengers[i]))
                throw new DoubleBookException("Passenger has already booked a seat!");
        }
        if (getSeatsAvailable(seatType)) {
            switch (seatType) {
                case FIRST_CLASS:
                    firstClassSeats[seatsInFirstCount].addPassenger(person);
                    person.setSeatNum(firstClassSeats[seatsInFirstCount++]);
                    break;

                case BUSINESS_CLASS:
                    businessClassSeats[seatsInBusinessCount].addPassenger(person);
                    person.setSeatNum(businessClassSeats[seatsInBusinessCount++]);
                    break;

                case ECONOMY_CLASS:
                    economyClassSeats[seatsInEconCount].addPassenger(person);
                    person.setSeatNum(economyClassSeats[seatsInEconCount++]);
                    break;
                    
                default:
                    return false;
            }
            seatsAvailable--;
            addPassenger(person);
            return true;
        }
        return false;
    }

    //adds passenger to the flight by storing the passenger in the flights list of passengers array
    //this method is called inside bookSeat method which already checks for seat availability
    //therefore; if this method is called, a passenger will always be able to be added to array
    private final void addPassenger(Passenger person) {
        passengers[numPassengers++] = person;
    }

    public final int getNumPassengers() {
        return numPassengers;
    }

    public final Passenger getPassenger(int index) throws NullPassengerException {
        if (passengers[index] == null)
            throw new NullPassengerException("Flight does not contain passenger at index: " + index);
        return passengers[index];
    }

    public final String printFlightInfo() {
        String passengers = String.format("Number of passengers: %d", numPassengers);
        String myString = String.format("Flight#: %1$s\nDeparting from: %2$s\nArriving to: %3$s\n%4$s\nPlane: %5$s %6$s"
        , flightNum, departFrom, arriveTo, passengers, planeType.COMPANY, planeType.CLASSIFICATION);
        return myString;
    }

    //returns a String of a Flight object as unique flight information
    @Override
    public final String toString() {
        String myString = String.format("Flight#: %s", flightNum);
        return myString;
    }

    //compares 2 Flight objects by comparing their object Strings
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Flight) {
            Flight cast = (Flight)obj;
            return this.getId() == cast.getId();
        }
        return false;
    }

    //Flight objects hash code is set to its id#
    @Override
    public final int hashCode() {
        return this.getId();
    }
    
}
