import java.io.Serializable;
import java.util.HashMap;

//constructs passenger with name and seatname
public class SeatMap implements Serializable
{
	HashMap<Seat, String> seatMap;
//	private Seat seat; 
	
	public SeatMap(Seat seat, String passengerName)
	{
		this.seatMap = new HashMap<>();
		this.seatMap.put(seat, passengerName);
	}
	
	/*public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}*/
	
}
