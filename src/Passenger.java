import java.io.Serializable;

//constructs passenger with name and seatname
public class Passenger implements Serializable
{
	private String name;
	private String seatName; 
	
	public Passenger(String name, String seatName)
	{
		this.name=name;
		this.seatName=seatName;
	}
	public String getSeatName()
	{
		return seatName;
	}
	public void setSeatName(String seatName)
	{
		this.seatName=seatName;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
}
