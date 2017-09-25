//new plane flight 
public class TransCanada extends AirplaneModel
{
		public TransCanada()
		{
			super();	//calls constructor of the superclass
			int i=0; 	//instance vars
			int index=28;
			
			for(int k = 0; k < CONSTANT*4; k+= CONSTANT)//4rows
			{	
				while(i < CONSTANT*4)//for loop for drawing the seats//4cols
				{			
					Seat newSeat = new Seat(CONSTANT/2+i, CONSTANT/2+k, CONSTANT/2);
					seatList.add(newSeat);

					char colChar= '\0';//to get a single letter at a time.
					i+=CONSTANT;     
					rowNum = (i/CONSTANT);
					colNo = (k/CONSTANT % 7 + 1); //  + 1

					switch (colNo) 
					{
					case 1:
						colChar = 'E';
						break;
					case 2:
						colChar = 'F';
						break;
					case 3:
						colChar = 'G';
						break;
					case 4:
						colChar = 'H';
						break;
					default:
						break;
						
					}
					
					//communicates with drawstring to set label of seat
					seatLabel = Character.toString(colChar) + Integer.toString(rowNum);
					seatList.get(index).setSeatName(seatLabel);

					String seatNum = seatList.get(index).getSeatName(); 
					colNo= getSeatNo(seatNum);
					index ++;
				}
				
				i = 0;
			}
			
			
		}
	}

