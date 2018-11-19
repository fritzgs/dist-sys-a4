package a5_rmi_fritz20071968;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RMI extends UnicastRemoteObject implements Calculator {

	
	public RMI() throws RemoteException
	{
		double a, b;
		String input;
	}

	@Override
	public double add(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a + b;
	}

	@Override
	public double subtract(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a - b;
	}

	@Override
	public double multiply(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a * b;
	}

	@Override
	public double divide(double a, double b) throws RemoteException {
		// TODO Auto-generated method stub
		return a / b;
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
		Pattern operator = Pattern.compile("(?!^)[-+*/]");
		Matcher match = operator.matcher(input);
		if(match.find())
		{
			return match.group(0).charAt(0);
		}
		return 0;
		
	}
	
	@Override
	public double operand1(String input) throws RemoteException
	{
		String nums = input.replaceFirst("(?!^)[-+*/]", "/");

		return Double.parseDouble(nums.substring(0, nums.indexOf("/")));
		
	}
	
	@Override
	public double operand2(String input) throws RemoteException
	{

		String nums = input.replaceFirst("(?!^)[-+*/]", "/");
		return Double.parseDouble(nums.substring(nums.indexOf("/")+1, nums.length()));
	}
}
