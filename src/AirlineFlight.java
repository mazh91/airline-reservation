import java.io.Serializable;
import java.util.HashMap;
//class that creates a flight
public class AirlineFlight  implements Serializable
{
	AirplaneModel airplaneModel;
	AirplaneComponent newComp = new AirplaneComponent();
	
//	ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
	transient HashMap<String, Boolean> passengerList = new HashMap<String, Boolean>();
	private String name;
	private String flightNum;
	private String seatName;

	
	public AirlineFlight(String flightNum, AirplaneModel model)
	{
		this.flightNum = flightNum;
//		System.out.println(">> AIRLINE FLIGHT IS CALLED");
		airplaneModel  = model;
		
	}
	// Add passenger
	void addPassenger(String name, String seatName)
	{
		passengerList.put(name, true);
	}
	// Search through seat list to obtain selected seat
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
	
	// Assign seats to passengers
	ErrorCodes assignSeat(String passengerName, int x, int y)//search through passenger list and adds pass if certain conditions are met.
	{
		boolean isAlreadyOnFlight = false;
		Seat seat = getSeat(x, y);
		if( seat != null)
		{
			// 3rd Attempt
			if(seat.getPassengerName() != null && seat.getPassengerName().equals(passengerName))
			{
				seat.setIsOccupied(false);
				passengerList.put(passengerName, false);	
				seat.setPassengerName(null);
				return ErrorCodes.NORMAL;
			}
			
			isAlreadyOnFlight = false;
			for (String pName : passengerList.keySet()) {
				if ( pName.equals(passengerName) && passengerList.get(pName) )
					isAlreadyOnFlight = true;
			}
					
			// Validity Check: passenger name 
			if ( passengerName.length() == 0 ){
//				NameBorder.setBorder(Color.RED);
				return ErrorCodes.EMPTY_FIELD; // throw exception;
			}
			else
				for(int j=0; j < passengerName.length(); j++)
					if( ! Character.isLetter(passengerName.charAt(j))){
//						NameBorder.setBorder(Color.RED);
						return ErrorCodes.INVALID_FIELD; // throw exception;
					}
			// Add new passenger
			if ( ! isAlreadyOnFlight ) 
			{
				System.out.println("seat selected!");
//				Passenger passenger = new Passenger ( passengerName, seat.getSeatName() );
				seat.setPassengerName(passengerName);
				passengerList.put(passengerName, true);
				getSeat(x,y).setIsOccupied(true);
				System.out.println( "seat " + seat.getSeatName() + " is occupied? " + seat.getIsOccupied());
			}
		}
		return ErrorCodes.NORMAL;
	}
	
	// Getters and setters
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

	public String getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

}
