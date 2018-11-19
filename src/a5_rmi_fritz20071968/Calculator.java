package a5_rmi_fritz20071968;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote{
	
	public double add(double a, double b) throws RemoteException;
	
	public double subtract(double a, double b) throws RemoteException;
	
	public double multiply(double a, double b) throws RemoteException;
	
	public double divide(double a, double b) throws RemoteException;
	
	public boolean valid(String input) throws RemoteException;
	
	public char operator(String input) throws RemoteException;
	
	public double operand1(String input) throws RemoteException;
	
	public double operand2(String input) throws RemoteException;

	
}
