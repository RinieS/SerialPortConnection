
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

import com.fazecast.jSerialComm.*;


public class AppMain {

	static SerialPort selected;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		
		   JFrame f = new JFrame("Temperature reader");
		   //set size and location of frame
		   f.setSize(600, 400);
		   f.setLayout(new BorderLayout());
		   //make sure it quits when x is clicked
		   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		   
		   //drop down for the selection of ports 
		   JComboBox<String>portList = new JComboBox<String>();
		   JButton connect = new JButton("Connect");
		   JPanel tp = new JPanel();
		   tp.add(portList);
		   tp.add(connect);
		   f.add(tp, BorderLayout.NORTH);
		   
		   //get ports for drop down list 
		   
		   SerialPort[] ports = SerialPort.getCommPorts();
			for (SerialPort port: ports) {
				portList.addItem(port.getSystemPortName());
			    System.out.println(port.getSystemPortPath());
			
			}
			
			
		   JLabel labelM = new JLabel("Temperature read in Celcius: ");
		   labelM.setBounds(10, 100, 200, 30);
		   JTextField result = new JTextField(5);
		   //set size of the text box
		   
		   result.setBounds(10, 100, 200, 30);
		   result.setEditable(false);
		   result.setVisible(true);
		   //add elements to the frame
		  JPanel bp = new JPanel();
		  bp.add(labelM);
		   bp.add(result);
		   
		   
		   f.add(bp, BorderLayout.CENTER);
		
		
		// connect button action listener 
		   
		   connect.addActionListener(new ActionListener() {
			   @Override 
			   public void actionPerformed(ActionEvent arg0) {
				   if(connect.getText().equals("Connect")) {
					   selected = SerialPort.getCommPort(portList.getSelectedItem().toString());
					   selected.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					   if(selected.openPort()) {
						   connect.setText("Disconnect");
						   portList.setEnabled(false);
					   }
					  
					   Thread thread = new Thread() {
						   @Override 
						   public void run() {
							   BufferedReader br = new BufferedReader(new InputStreamReader (selected.getInputStream()));
							   String value = "0"; 
								try {
									while ((value = br.readLine()) != null) {
									
									result.setText(value);
   }
								} catch (IOException e) {
									
									e.printStackTrace();
								}
					   }
						  
				   };
				   thread.start();
			   }else {
				   selected.closePort();
				   portList.setEnabled(true);
				   connect.setText("Connect");
			   }
		   }

			
		   });
	
		   f.setVisible(true);
	
		
		
		

		
	}

}
	

