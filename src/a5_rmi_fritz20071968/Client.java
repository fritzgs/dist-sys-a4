package a5_rmi_fritz20071968;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.text.Text;

/**
 * Client class with calculator GUI.
 * This class handles the button actions of the calculator and sends the data to the RMI registry once submitted.
 * The RMI registry method will return back the result for it to be displayed on this GUI.
 * @author Fritz Gerald Santos - 20071968
 *
 */

public class Client implements ActionListener{
		
	//Text fields
	private JTextField screen; //for input
	private JTextArea resultsScreen; //for results
	
	private String input; //the input of the user

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Client(); //creating a new instance of this class.
	}
	
	Client()
	{
		input = ""; //setting the input to nothing initially.
		
		
		//GUI
		//Frame/Window
		JFrame calcFrame = new JFrame();
		calcFrame.setTitle("Calculator - Client");
		calcFrame.setSize(400,300);
		screen = new JTextField();
		screen.setEditable(false);
		screen.setHorizontalAlignment(JTextField.RIGHT);
		
		resultsScreen = new JTextArea();
		resultsScreen.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));
		
		//loop through 9 down to 0 and create buttons.
		for (int i = 9; i >= 0; i--)
		{
			String name = String.valueOf(i);
			JButton numbers = new JButton(name);
			numbers.setActionCommand(name);
			numbers.addActionListener(this);
			panel.add(numbers);

		}
		
		//plus operator button
		JButton plus = new JButton("+");
		plus.setActionCommand("plus");
		plus.addActionListener(this);
		panel.add(plus);
		
		//minus operator button
		JButton minus = new JButton("-");
		minus.setActionCommand("minus");
		minus.addActionListener(this);
		panel.add(minus);
		
		//divide operator button
		JButton divide = new JButton("/");
		divide.setActionCommand("divide");
		divide.addActionListener(this);
		panel.add(divide);
		
		//multiply operator button
		JButton multiply = new JButton("*");
		multiply.setActionCommand("multiply");
		multiply.addActionListener(this);
		panel.add(multiply);

		//decimal button
		JButton dot = new JButton(".");
		dot.setActionCommand("dot");
		dot.addActionListener(this);
		panel.add(dot);
		
		//equals/submit button
		JButton equals = new JButton("=");
		equals.setActionCommand("equals");
		equals.addActionListener(this);
		panel.add(equals);
		
		//add all to frame
		calcFrame.add(screen, BorderLayout.NORTH);
		calcFrame.add(resultsScreen, BorderLayout.CENTER);
		calcFrame.add(panel, BorderLayout.SOUTH);
		
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calcFrame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		/*all the buttons in the frame - 
		* if clicked -> appends character to the last index of the string 
		* and displays on text field.
		*/
		if(command.equals("0")) { input += "0"; screen.setText(input); }
		else if(command.equals("1")) { input += "1"; screen.setText(input); }
		else if(command.equals("2")) { input += "2"; screen.setText(input); }
		else if(command.equals("3")) { input += "3"; screen.setText(input); }
		else if(command.equals("3")) { input += "3"; screen.setText(input); }
		else if(command.equals("4")) { input += "4"; screen.setText(input); }
		else if(command.equals("5")) { input += "5"; screen.setText(input); }
		else if(command.equals("6")) { input += "6"; screen.setText(input); }
		else if(command.equals("7")) { input += "7"; screen.setText(input); }
		else if(command.equals("8")) { input += "8"; screen.setText(input); }
		else if(command.equals("9")) { input += "9"; screen.setText(input); }
		else if(command.equals("plus")) { input += "+"; screen.setText(input); }
		else if(command.equals("minus")) { input += "-"; screen.setText(input); }
		else if(command.equals("divide")) { input += "/"; screen.setText(input); }
		else if(command.equals("multiply")) { input += "*"; screen.setText(input); }
		else if(command.equals("dot")) { input += "."; screen.setText(input); }

		//when submitted
		else if(command.equals("equals"))
		{
			try
			{
				//creates gets the date and time when clicked.
				String date = String.valueOf(new Date()) + ":  ";
				//connects to localhost server (RMI registry)
				Calculator calc = (Calculator) Naming.lookup("//localhost/Calculator");
				if(calc.valid(input)) //checks if the input of the user is a valid equation to calculate.
				{
					//gets the operands of the input - allows +/- double operand values.
					double a = calc.operand1(input);
					double b = calc.operand2(input);
					char operator = calc.operator(input); //gets the operator of the equation.
					if(operator == '+') 
					{
						resultsScreen.append(date + input + " = " + calc.add(a,b) + "\n");
					}
					else if(operator == '-')
					{
						resultsScreen.append(date + input + " = " + calc.subtract(a,b) + "\n");
					}
					else if(operator == '/')
					{
						resultsScreen.append(date + input + " = " + calc.divide(a,b) + "\n");

					}
					else
					{
						resultsScreen.append(date + input + " = " + calc.multiply(a,b) + "\n");
					}
					
					//reset the input screen to blank.
					screen.setText("");
				}
				else //if the input is not a valid equation - it will print this.
				{
					resultsScreen.append(date + input + " is not valid! - Try again... \n");
				}
				//when calculation is complete or input is incorrect, set the input back to empty string.
				input = "";
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	

}
