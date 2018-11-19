package a5_rmi_fritz20071968;

import java.awt.BorderLayout;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Server {
	public static void main(String[] args) {
		
//		JFrame frame = new JFrame();
//		frame.setSize(300,300);
//		frame.setTitle("Server");
//		
//		JTextArea screen = new JTextArea();
//		screen.setEditable(false);
//		
//		frame.add(screen, BorderLayout.CENTER);
//		
//		
//		frame.setVisible(true);

		
		try
		{
			Registry rmireg = LocateRegistry.createRegistry(1099);
			rmireg.rebind("Calculator", new RMI());
			System.out.println((String.valueOf(new Date()) + ": Server is Connected."));
//			screen.append((String.valueOf(new Date()) + ": Server is Connected."));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	
	}

}
