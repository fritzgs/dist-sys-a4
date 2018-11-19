package a5_rmi_fritz20071968;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.text.Text;


public class Client implements ActionListener{
	private DecimalFormat decimal;
	
	private JTextField screen;
	private JTextArea resultsScreen;
	
	private String input;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Client();
	}
	
	Client()
	{
		decimal = new DecimalFormat(".#####");
		input = "";
		
		
		//GUI
		
		JFrame calcFrame = new JFrame();
		calcFrame.setTitle("Calculator");
		calcFrame.setSize(400,300);
		screen = new JTextField();
		screen.setEditable(false);
		screen.setHorizontalAlignment(JTextField.RIGHT);
		
		resultsScreen = new JTextArea();
		resultsScreen.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));
		
		for (int i = 9; i >= 0; i--)
		{
			String name = String.valueOf(i);
			JButton numbers = new JButton(name);
			numbers.setActionCommand(name);
			numbers.addActionListener(this);
			panel.add(numbers);

		}
		
		JButton plus = new JButton("+");
		plus.setActionCommand("plus");
		plus.addActionListener(this);
		panel.add(plus);
		
		JButton minus = new JButton("-");
		minus.setActionCommand("minus");
		minus.addActionListener(this);
		panel.add(minus);
		
		JButton divide = new JButton("/");
		divide.setActionCommand("divide");
		divide.addActionListener(this);
		panel.add(divide);
		
		JButton multiply = new JButton("*");
		multiply.setActionCommand("multiply");
		multiply.addActionListener(this);
		panel.add(multiply);

		JButton dot = new JButton(".");
		dot.setActionCommand("dot");
		dot.addActionListener(this);
		panel.add(dot);
		
		JButton equals = new JButton("=");
		equals.setActionCommand("equals");
		equals.addActionListener(this);
		panel.add(equals);
		

		calcFrame.add(screen, BorderLayout.NORTH);
		calcFrame.add(resultsScreen, BorderLayout.CENTER);
		calcFrame.add(panel, BorderLayout.SOUTH);
		
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calcFrame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
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

		else if(command.equals("equals"))
		{
			try
			{
				String date = String.valueOf(new Date()) + ":  ";
				Calculator calc = (Calculator) Naming.lookup("//localhost/Calculator");
				if(calc.valid(input))
				{
					double a = calc.operand1(input);
					double b = calc.operand2(input);
					char operator = calc.operator(input);
					if(operator == '+')
					{
						resultsScreen.append(date + input + " = " + calc.add(a,b)+ "\n");
					}
					else if(operator == '-')
					{
						resultsScreen.append(date + input + " = " + calc.subtract(a,b)+ "\n");
					}
					else if(operator == '/')
					{
						resultsScreen.append(date + input + " = " + calc.divide(a,b)+ "\n");

					}
					else
					{
						resultsScreen.append(date + input + " = " + calc.multiply(a,b) + "\n");
					}
					
					screen.setText("");
				}
				else
				{
					resultsScreen.append(date + input + " is not valid! - Try again... \n");
				}
				input = "";
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	

}
