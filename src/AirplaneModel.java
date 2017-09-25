import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;
//creates a Model of an airplane with seats  determined by columns and rows
public class AirplaneModel extends JComponent  implements Serializable
{
	//instance vars	
	int vertDet = 0;
	ArrayList<Seat> seatList =  new ArrayList<Seat>();//array of seat objs req to draw them into sqrs
	int colNo=0, rowNum=0, xPos, yPos, height;
	final int CONSTANT = 60, WIDTH = 30; 
	String seatLabel;
	

	public AirplaneModel()
	{
		int i = 0;

		//length minus 1 =taking last letter // length is actually the length; index should start at zero
		//three params because seats are sqr thus height = width
		int index = 0;
		int incrementor = 350;
		//add varname.
		//this loop detemines which row of the airplane is being referred to
		for(int k = 0; k < CONSTANT*4; k+= CONSTANT)//4rows
		{	
			while(i < CONSTANT*7)//for loop for drawing the seats//7cols
			{			
				Seat newSeat = new Seat(CONSTANT/2+i+incrementor, CONSTANT/2+k, CONSTANT/2);
				seatList.add(newSeat);

				char colChar= '\0';//to get a single letter at a time.
				i+=CONSTANT;     
				rowNum = (i/CONSTANT);
				colNo = (k/CONSTANT % 7) + 1;

				switch (colNo) 
				{
				case 1:
					colChar = 'A';
					break;
				case 2:
					colChar = 'B';
					break;

				case 3:
					colChar = 'C';
					break;
				case 4:
					colChar = 'D';
					break;
				default:
					break;
					
				}
				
				//communicated with drawstring to set label of seat
				seatLabel = Character.toString(colChar) + Integer.toString(rowNum);
				seatList.get(index).setSeatName(seatLabel);

				String seatNum = seatList.get(index).getSeatName(); 
				colNo= getSeatNo(seatNum);
				index ++;
			}
			
			i = 0;
		}incrementor = 350;
	}
 //gets seat number by first char and catting it to number.
	public int getSeatNo(String seatNumber)
	{
		int length = seatNumber.length();
		String seatRowNoStr = seatNumber.substring(length-1);
		final int MOD_FACTOR = 4;
//		char seatColNoChar = (char) (seatNumber.charAt(0) % MOD_FACTOR +('A'-1)); 
		int seatCode = seatNumber.charAt(0) % MOD_FACTOR;


		switch (seatCode) 
		{
		case 1:
			colNo = 1;
			break;
		case 2:
			colNo = 2;
			break;
		case 3:
			colNo = 3;
			break;
		case 0:
			colNo = 4;
			break;
		default:
			System.out.println("ERROR: " + seatNumber);
			break;
		}
		/*
		switch (seatColNoChar) 
		{
		case 'A':
			colNo = 1;
			break;
		case 'B':
			colNo = 2;
			break;
		case 'C':
			colNo = 3;
			break;
		case 'D':
			colNo = 4;
			break;
		default:
			System.out.println("ERROR: " + seatColNoChar);
			break;
		}
*/
		int rowNum = Integer.parseInt(seatRowNoStr);
		int seatNum = rowNum*10 + colNo; // (index of arraylist)cats col and row numbers
		return seatNum;
	}

}


