
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

import com.fazecast.jSerialComm.*;


public class AppMain {

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		SerialPort[] ports = SerialPort.getCommPorts();
		for (SerialPort port: ports)
		    System.out.println(port.getSystemPortPath());
	

		SerialPort sp = SerialPort.getCommPort(ports[1].getSystemPortPath());
		sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
		sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
    
    
		boolean hasOpenned = sp.openPort();
		if(hasOpenned) {
		System.out.println("connected");
	}
		else {
		throw new IllegalStateException ("Failed to open");
	}
		
		
		
		   JFrame f = new JFrame("Temperature reader");
		   //set size and location of frame
		   f.setSize(390, 300);
		   f.setLocation(100, 150);
		   //make sure it quits when x is clicked
		   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   //set look and feel
		   f.setDefaultLookAndFeelDecorated(true);
		   JLabel labelM = new JLabel("Temperature read in Celcius: ");
		   labelM.setBounds(50, 50, 200, 30);
		   JTextField result = new JTextField();
		   //set size of the text box
		   result.setBounds(50, 100, 200, 30);
		   result.setEditable(false);
		   //add elements to the frame
		   f.add(labelM);
		   f.add(result);
		   f.setLayout(null);
		   f.setVisible(true);
		
		
	
		BufferedReader br = new BufferedReader(new InputStreamReader (sp.getInputStream()));
		//Scanner data = new Scanner (sp.getInputStream());
		String value = "0"; 
		while (true) {
		
		value = br.readLine();
		//System.out.println(value);
		result.setText(value);

		
	}

}
	
}
