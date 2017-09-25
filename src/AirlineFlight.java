import java.io.Serializable;
import java.util.ArrayList;
//class that creates a flight
public class AirlineFlight  implements Serializable
{
	AirplaneModel airplaneModel;
	AirplaneComponent newComp = new AirplaneComponent();
	
	ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
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
		passengerList.add(passenger);
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
	//assigns seats
	void assignSeat(String passengerName, int x, int y)//search through passenger list and adds pass if certain conditions are met.
	{
		
		Seat seat = getSeat(x, y);
		if( seat != null)
		{

			isChangingSeat = false;
			isOnFlight = false;
			
			
//			 2nd Attempt
			for (Passenger passenger : passengerList) {
				if (passenger.getSeatName().equals(seat.getSeatName())) { // && seat.getSeatName().equals(anObject)  passenger.getName().equals(passengerName)
					if (seat.getIsOccupied() ) { 
						getSeat(x, y).setIsOccupied(false);
						passengerList.remove(passenger);
						isChangingSeat = true;
						break;
					}
					return;
				}
			}
			
			if(seat.getIsOccupied())
				return;
			
				/*for(int i = 0; i < passengerList.size(); i++)
				{
					
					// passenger already is on the flight!
					System.out.println( "passenger at i: " + passengerList.get(i).getName() );
					if ( passengerName.equals(passengerList.get(i).getName()) && !getSeat(x, y).getIsOccupied() ) 
						throw new IllegalArgumentException();

					System.out.println("current seat no:" + getSeat(x, y).getSeatName() + "/ list seat no:" + passengerList.get(i).getSeatName());
					// passenger wants to change seat
					if(getSeat(x,y).getSeatName().equals( passengerList.get(i).getSeatName()) && passengerName.equals(passengerList.get(i).getName() )) 						//if seatname and passname are not the same; then its a new passenger.
					{
						getSeat(x,y).setIsOccupied(false);
						passengerList.remove(i);
						isChangingSeat = true;
					}
	
					else if ( passengerName.equals(passengerList.get(i).getName()) )	
						// if there's no "else" beside this if, when there are no passengers, 
//						 (the previous condition removed it) java's gonna throw exception 
//						 b/c it's trying to access null pointer
							isOnFlight = true;
				}*/
				
				IllegalArgumentException exception = new IllegalArgumentException();//cannot initialize inside the if
				
				if ( passengerName.length() == 0 )
					throw exception;
				else
					for(int j=0; j < passengerName.length(); j++)//goes through each letter, error checking for nonalphachars
					{
						if( !Character.isLetter(passengerName.charAt(j)))
							throw exception;
					}
				
				// add new passenger
				if ( isChangingSeat == false && !isOnFlight ) 
				{
					System.out.println("seat selected!");
					Passenger passenger = new Passenger ( passengerName, getSeat(x,y).getSeatName() );
					passengerList.add(passenger);
					getSeat(x,y).setIsOccupied(true);
					System.out.println( "seat " + getSeat(x,y).getSeatName() + " is occupied? " + getSeat(x,y).getIsOccupied());
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
