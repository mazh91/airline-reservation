import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
//class that creates a flight
public class AirlineFlight  implements Serializable
{
	AirplaneModel airplaneModel;
	AirplaneComponent newComp = new AirplaneComponent();
	
//	ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
	HashMap<String, Boolean> passengerList = new HashMap<String, Boolean>();
	private String name;
	private String flightNum;
	private String seatName;
	private boolean isOnFlight = false;	// current passenger exists on flight
	private boolean isChangingSeat = false;	// current passenger wants to change their seat
	
	public AirlineFlight(String flightNum, AirplaneModel model)
	{
		this.flightNum = flightNum;
		System.out.println(">> AIRLINE FLIGHT IS CALLED");
		
			airplaneModel  = model;
		
	}
	//add passenger
	void addPassenger(String name, String seatName)
	{
		Passenger passenger = new Passenger (name, seatName);
		passengerList.put(name, true);
	}
	//searches through seatlist to obtain current seat
	Seat getSeat(int x, int y)
	{   
		Seat seat = null ;
		for(int i=0; i < airplaneModel.seatList.size(); i++)
		{
			//determines whether square seat has been selected at x,y
			if(x >= airplaneModel.seatList.get(i).getXleft() && x <= airplaneModel.seatList.get(i).getXleft() + airplaneModel.WIDTH)
				if(y >= airplaneModel.seatList.get(i).getYtop() && y <= airplaneModel.seatList.get(i).getYtop() + airplaneModel.WIDTH)
					seat = airplaneModel.seatList.get(i);
		}

		return seat;  
	}	
	// TODO: Cannot select previously assigned seat to same passenger
	//assigns seats
	void assignSeat(String passengerName, int x, int y)//search through passenger list and adds pass if certain conditions are met.
	{
		
		Seat seat = getSeat(x, y);
		if( seat != null)
		{
//			Passenger dummyPassenger = new Passenger(passengerName, seat.getSeatName());
			// 3rd Attempt
			if(seat.getPassengerName() != null && seat.getPassengerName().equals(passengerName))
			{
				seat.setIsOccupied(false);
				passengerList.put(passengerName, false);	
				seat.setPassengerName(null);
				return;
			}
			
			isChangingSeat = false;
			isOnFlight = false;
			for (String pName : passengerList.keySet()) {
				if ( passengerList.get(pName) && pName.equals(passengerName) )
					isOnFlight = true;
			}
					
			if ( passengerName.length() == 0 )
				return; // throw exception;
			else
				for(int j=0; j < passengerName.length(); j++)//goes through each letter, error checking for nonalphachars
				{
					if( !Character.isLetter(passengerName.charAt(j)))
						return; // throw exception;
				}
			
			// add new passenger
			if ( isChangingSeat == false && !isOnFlight ) 
			{
				System.out.println("seat selected!");
				Passenger passenger = new Passenger ( passengerName, seat.getSeatName() );
				seat.setPassengerName(passengerName);
				passengerList.put(passengerName, true);
				getSeat(x,y).setIsOccupied(true);
				System.out.println( "seat " + seat.getSeatName() + " is occupied? " + seat.getIsOccupied());
			}
		}
	}
	//getters and setters
	public String getSeatName() 
	{
		return seatName;
	}

	public void setSeatName(String seatName) 
	{
		this.seatName = seatName;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public boolean isOnFlight() {
		return isOnFlight;
	}

	public void setOnFlight(boolean isOnFlight) {
		this.isOnFlight = isOnFlight;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

}
