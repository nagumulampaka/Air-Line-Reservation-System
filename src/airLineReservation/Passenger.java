package airLineReservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Passenger {
	
	static ArrayList<Flight> list = new ArrayList();
	static ArrayList<Reservation> reser = new ArrayList();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		list.add(new Flight(4523,"Paris",5));
		list.add(new Flight(7992,"Hyderbad",10));
		list.add(new Flight(8080,"England",8));
		
		while(true) {
			System.out.println("\n-----Welcome To Air Line Reservation System-----");
			System.out.println("1.Display Availabe Seats");
			System.out.println("2.Book A Flight");
			System.out.println("3.View Reservations Details");
			System.out.println("4.Cancel Booking");
			System.out.println("5.Exit");
			System.out.println("Choose an Option");
			int choice = getValidIntegerInput();
			switch(choice) {
			case 1:{
				displayAvailableSeats();
				break;
			}
			case 2:{
				bookFlight();
				break;
			}
			case 3:{
				viewReservationDetails();
				break;
			}
			case 4:{
				cancelBooking();
				break;
			}
			case 5:{
				System.out.println("Thank you for using Air Line Reservation Booking.");
				scanner.close();
				return;
			}
			default :{
				System.out.println("invalid option please try again.");
			}
			}
			
		}
		
	}		

	private static void cancelBooking() {
        System.out.println("enter the name of the passenger to cancel the flight");	
        String passengerName = scanner.next();
        Reservation reservationToCancel=null;
        for(Reservation r:reser)
        {
        	if(r.getName().equalsIgnoreCase(passengerName))
        	{
        		reservationToCancel=r;
        		break;
        	}
        }
        if(reservationToCancel!=null) {
        Flight flight= reservationToCancel.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats()+1);
        flight.setAvailableSeats(flight.getAvailableSeats());
        reser.remove(reservationToCancel);
        System.out.println("Reservation cancel for the passenger");
        }
        else {
        	System.out.println("No Reservations made yet with the name :"+passengerName);
        }
        
	}

	private static void viewReservationDetails() {
     if(reser.isEmpty()) {
    	 System.out.println("no reservation till now");
     }else {
    	 System.out.println("Reservations------");
    	 for(Reservation r : reser) {
    		 System.out.println("Passenger Name"+r.getName());
    		 System.out.println("Flight Number"+r.getFlight().getFlightNumber());
    		 System.out.println("Destination"+r.getFlight().getDesignation());
    		 System.out.println("-------------------");
    	 }
     }
	}

	private static void bookFlight() {
		displayAvailableSeats();
		System.out.println("enter the flight number to book a flight");
		int flightNumber = getValidIntegerInput();
	    Flight selectedFlight = null;
	    for(Flight flight:list) {
	    	if(flight.getFlightNumber()==flightNumber) {
	    		selectedFlight=flight;
	    		break;
	    	}
	    }
	    if(selectedFlight==null)
	    {
	    	System.out.println("invalid flight number.try again");
	    	return;
	    }
	    if(selectedFlight.getAvailableSeats()>0) {
	    	System.out.println("please enter your name");
	    	String passengerName=scanner.next();
	    	Reservation reservation = new Reservation(passengerName,selectedFlight);
	    	reser.add(reservation);
	    	selectedFlight.decreaseAvaliableSeats();
	    	System.out.println("Your Booking Successfully....");
	    }else {
	    	System.out.println("sorry seats are not available in the selected flight.");
	    }
	}

	private static void displayAvailableSeats() {
     System.out.println("\n-----Available Flights-----");
     for(Flight f:list) {
    	 System.out.println("Fight Number :"+f.getFlightNumber()+"Destination :"+f.getDesignation()+"Available Seats :"+f.getAvailableSeats());
     }
	}
	
	



	private static int getValidIntegerInput(){
		while(!scanner.hasNextInt()) {
			System.out.println("Please Enter the Proper Number");
			scanner.next();
		}
		return scanner.nextInt();
	}

}
