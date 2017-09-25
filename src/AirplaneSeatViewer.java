import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import java.util.*;

//tests the Airline PRGRM
public class AirplaneSeatViewer
{
	//instance vars
	public static int randNo;
	final static int CONSTANT = 20;
//	public static Color nameBorderColor = Color.BLACK;

//	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException
	{
//		AirlineFlight flight;

		final AirplaneComponent flightComp = new AirplaneComponent();

		ArrayList<AirlineFlight> newFlightList = new ArrayList<AirlineFlight>();

		final File file = new File ("flight.dat");
		
		// add the flights to the flight list
//		flight = new AirlineFlight("6387", new AirplaneModel() );
		flightComp.flightList.add( new AirlineFlight( "6387", new AirplaneModel() ) );
		
//		flight = new AirlineFlight( "7421", new AirCanada() );
		flightComp.flightList.add( new AirlineFlight( "7421", new AirCanada() ));
		
		flightComp.flightList.add( new AirlineFlight( "2736", new TransCanada() ) );
		
		for (AirlineFlight flight : flightComp.flightList)
			flightComp.flightListSelection.addItem( flight.getFlightNum() );
			
//		for ( flightComp.flightList)
		
		if (file.exists())
		{	
			ObjectInputStream in = null;
			try{
				in = new ObjectInputStream(new FileInputStream(file));
//				newFlightList = (ArrayList<AirlineFlight>) in.readObject();
//				Object dummy = in.readObject();
				ArrayList<?> dummyList = (ArrayList<?>) in.readObject();
				for(Object dummy : dummyList)
					if (dummy instanceof AirlineFlight)
						newFlightList.add( (AirlineFlight)dummy );
				// copy the new arraylist to the one that is used
				flightComp.flightList.clear();
	
				for( int i = 0 ; i < newFlightList.size() ; i++ )  
				{  
					flightComp.flightList.add( newFlightList.get( i ));  
				} 
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				if(in != null){
					try{
						in.close();
					} catch(IOException e){
						System.out.println("E: reading object stream");
					}
				}
			}
		}
		else
		{
			AirlineFlight flight1;

			if (flightComp.getFlightIndex() == 0)
			{
				flight1 = new AirlineFlight( "6387", new AirplaneModel() );
				flightComp.flightList.add(flight1);
			}

		}

		//can access airplaneModel with flight obj.

		final JFrame frame = new JFrame();
		final int FRAME_WIDTH  = 900;
		final int FRAME_HEIGHT = 400;

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Flight Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a panel to store our two buttons
		JPanel panel = new JPanel();

		// TODO: Highlight(red) name field on validity error
		// Add data fields to panel
		panel.add(flightComp.getNameField());
//		panel.add(flightComp.flightField);
//		panel.add(flightComp.selFlightBttn);
		panel.add(flightComp.flightListSelection);
		panel.add(flightComp.errorField);


		frame.add(flightComp.flightList.get(0).airplaneModel, BorderLayout.CENTER);
		frame.add(flightComp);

		JButton printButton = new JButton("Print Pass");
		frame.add(panel,BorderLayout.SOUTH);

		// TODO: Re-enable print functionality
		//prints pass
		class PrintListener implements ActionListener 
		{
			public void actionPerformed(ActionEvent printEvent)
			{	
				int flightNum = Integer.parseInt( flightComp.flightList.get(flightComp.getFlightIndex()).getFlightNum() );
				//to pick a random flight and remain unchanged.

				/*for(int i=0; i < flightComp.flightList.get(flightComp.getFlightIndex()).passengerList.size(); i++)
					if ( flightComp.flightList.get(flightComp.getFlightIndex()).passengerList.get(i).getName() != null )
						System.out.println("Name: " + flightComp.flightList.get(flightComp.getFlightIndex()).passengerList.get(i).getName()  + "\tSeat: "
								+ flightComp.flightList.get(flightComp.getFlightIndex()).passengerList.get(i).getSeatName() + "\tFlight: " + flightNum);
*/
			}

		}

		PrintListener printListener= new PrintListener();
		panel.add(printButton);
		printButton.addActionListener(printListener);
		JButton exitButton = new JButton("Exit PRGM");
		//exits and saves last occupied seats into file.
		class ExitListener implements ActionListener
		{

			public void actionPerformed(ActionEvent printEvent)
			{	
				ObjectOutputStream out;
				try {
					out = new ObjectOutputStream(new FileOutputStream(file));
					out.writeObject(flightComp.flightList);		
					out.close();
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}

				System.exit(0);
			}

		}

		ExitListener exitListener= new ExitListener();
		panel.add(exitButton);
		exitButton.addActionListener(exitListener);
		
		//allows usr to switch between flights
		class FlightListener implements ActionListener 
		{
			
			public void actionPerformed(ActionEvent flightSelected)
			{	
				String flightNum = flightComp.flightListSelection.getSelectedItem().toString();

				for(int i=0; i<flightComp.flightList.size(); i++)
					if( flightNum.equals(flightComp.flightList.get(i).getFlightNum()) )
						flightComp.setFlightIndex(i);
						// end of for
				flightComp.setFlightIndex(flightComp.getFlightIndex());
				flightComp.repaint();
			}

		}

		FlightListener flightListener= new FlightListener();
//		flightComp.selFlightBttn.addActionListener(flightListener);
		flightComp.flightListSelection.addActionListener(flightListener);

		frame.setVisible(true);//should always be the last line in viewer.
	}
}
