package a5_rmi_fritz20071968;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the RMI Registry that is used by the client to get results of inputs
 * @author Fritz Gerald Santos - 20071968
 */

public class RMI extends UnicastRemoteObject implements Calculator {

	private DecimalFormat decimal; //to round the double
	
	//Constructor
	public RMI() throws RemoteException
	{
		decimal = new DecimalFormat(".#####"); //rount to 4 decimal points.
	}

	@Override
	public double add(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return Double.parseDouble(decimal.format(a + b));
	}

	@Override
	public double subtract(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return Double.parseDouble(decimal.format(a - b));
	}

	@Override
	public double multiply(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return Double.parseDouble(decimal.format(a * b));
	}

	@Override
	public double divide(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return Double.parseDouble(decimal.format(a / b));
	}

	@Override
	public boolean valid(String input) throws RemoteException {
		
		if(Pattern.matches("^[\\-+]?\\d{1,}(\\.\\d{1,})?[-+*/][\\-+]?\\d{1,}(\\.\\d{1,})?$", input))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public char operator(String input) throws RemoteException
	{
		//this specifies the regular expression to use to find the match
		Pattern operator = Pattern.compile("(?!^)[-+*/]"); //regular expression for finding the first operator that is not at the start of the string
		Matcher match = operator.matcher(input);
		if(match.find())
		{
			return match.group(0).charAt(0); //return operator
		}
		return 0;
		
	}
	
	@Override
	public double operand1(String input) throws RemoteException
	{
		String nums = input.replaceFirst("(?!^)[-+*/]", "/"); //replaces the operator into "/"

		 //gets the first part of the string before the delimiter and converts to double
		return Double.parseDouble(nums.substring(0, nums.indexOf("/")));
		
	}
	
	@Override
	public double operand2(String input) throws RemoteException
	{
		
		String nums = input.replaceFirst("(?!^)[-+*/]", "/"); //replaces the operator into "/"
		//gets the first part of the string after the delimiter and converts to double
		return Double.parseDouble(nums.substring(nums.indexOf("/")+1, nums.length()));
	}
}
