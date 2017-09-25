import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.*;

import javax.swing.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AirplaneComponent extends JComponent implements Serializable
{	
	//instance vars	
	ArrayList<AirlineFlight> flightList = new ArrayList<AirlineFlight>();
	AirlineFlight flight; 
	final int CONSTANT = 0;
	final int randNo;
	private String text;
	private JTextField nameField;
	private int flightIndex;
	JTextField errorField = new JTextField(20);
	private boolean isFirstFlightAdded;
	private boolean isSecFlightAdded;
	private boolean isThirdFlightAdded;
//	final JButton selFlightBttn = new JButton("Switch Flight");
	final JComboBox<String> flightListSelection = new JComboBox<String>();
	
//	final JTextField flightField = new JTextField("6387");
	final JScrollPane scrollPane = new JScrollPane();
	final JTextArea textOutput = new JTextArea();
	


	//constructs airline of flights
	public AirplaneComponent()
	{
		setNameField(new JTextField(10));

		Random rand = new Random();
		randNo = rand.nextInt(3) +1;
		
		errorField.setEnabled(false);
		
		// Determines whether user has clicked on a seat
		class SeatSelected implements MouseListener
		{

			public void mouseClicked(MouseEvent event) 
			{ 
				flight = flightList.get(getFlightIndex());
					
				ErrorCodes rc = flight.assignSeat( getNameField().getText(), event.getX(), event.getY()-CONSTANT );
				
				if(rc.getId() < 0){
					nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
					errorField.setText("Enter your Name/Flight to reserve Seat");
					System.err.println("E: " + rc.getMsg());
				}
				else
					nameField.setBorder(UIManager.getBorder("TextField.border"));

				repaint();	//never repaint in a loop

			}

			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		}
		//adds listener 
		SeatSelected selectedSeat = new SeatSelected();
		addMouseListener(selectedSeat);

	}		// end of constructor

	//goes through seatlist and draws the seat columns and rows 
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		for (int j = 0; j < flightList.get(getFlightIndex()).airplaneModel.seatList.size(); j++)

			flightList.get(getFlightIndex()).airplaneModel.seatList.get(j).draw(g2);

	}
	
	
	// Getters and setters
/*	public static void setNameBorderColor(Color color){
		borderColor = color;
	}
	public static Color getNameBorderColor(){
		return borderColor;
	}
	*/
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setFlightIndex(int flightIndex) {
		this.flightIndex = flightIndex;
	}


	public int getFlightIndex() {
		return flightIndex;
	}

	public void setFirstFlightAdded(boolean isFirstFlightAdded) {
		this.isFirstFlightAdded = isFirstFlightAdded;
	}

	public boolean isFirstFlightAdded() {
		return isFirstFlightAdded;
	}

	public void setSecFlightAdded(boolean isSecFlightAdded) {
		this.isSecFlightAdded = isSecFlightAdded;
	}

	public boolean isSecFlightAdded() {
		return isSecFlightAdded;
	}

	public void setThirdFlightAdded(boolean isThirdFlightAdded) {
		this.isThirdFlightAdded = isThirdFlightAdded;
	}

	public boolean isThirdFlightAdded() {
		return isThirdFlightAdded;
	}
	
}
