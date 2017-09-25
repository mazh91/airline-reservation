import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
//constructs and draws square seat and sets position to x,y
public class Seat  implements Serializable
{
  Rectangle airplane;
  private int xLeft, yTop, width;
String passengerName;	
  private String seatName;
  private boolean isOccupied; 
  Rectangle square;
  
  public Seat(int xLeft, int yTop, int width)
  {
	  this.xLeft=xLeft;
	  this.yTop=yTop;
	  this.width=width;
	  square = new Rectangle(xLeft,yTop,width,width);
  }  
  public String getSeatName()
  {
	  return seatName;
  }
  public void setSeatName(String seatName)
  {
	  this.seatName=seatName;
  }
  public int getXleft()
  {
	  return xLeft;
  }
  public void setXleft(int xLeft)
  {
	  this.xLeft=xLeft;
  }
  public int getYtop()
  {
	  return yTop;
  }
  public void setYtop(int yTop)
  {
	  this.yTop=yTop;
  }
  public int getWidth()
  {
	  return width;
  }
  public void setWidth(int width)
  {
	  this.width= width;
  }
  public boolean getIsOccupied()
  {
	  return isOccupied;
  }
  public void setIsOccupied(boolean isOccupied)
  {
	  this.isOccupied= isOccupied;
  }
  
  public void draw(Graphics2D g2)//seat draws itself
  {
		if(getIsOccupied() == true)
		{
			g2.setColor(Color.RED);
			g2.draw(square);	
		}
	    
	    else
		{
			g2.setColor(Color.GRAY);	
			g2.draw(square);
		}
		//can only cast like this -> (int)....when casting two compatible chars 
	    if (getSeatName() != null)
	    	g2.drawString(getSeatName() ,(int) square.getCenterX(), (int) square.getCenterY());
  }
  
  public String getPassengerName() 
  {
	return passengerName;
  }
  
  public void setPassengerName(String passengerName) 
  {
	this.passengerName = passengerName;
  }
  
}
