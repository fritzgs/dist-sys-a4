package a5_rmi_fritz20071968;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;

class RMITest {
	
	String equation = "-22.57+102.57";
	double operand1 = -22.57;
	double operand2 = 102.57;
	RMI rmi;
	
	void setUp() throws RemoteException
	{
		rmi = new RMI();
		
	}

	@Test
	void addTest() throws RemoteException {
		setUp();
		assertEquals(80.0, rmi.add(operand1, operand2));
	}
	
	@Test
	void minusTest() throws RemoteException
	{
		setUp();
		assertEquals(-125.14, rmi.subtract(operand1, operand2));
	}
	
	@Test
	void multiplyTest() throws RemoteException
	{
		setUp();
		assertEquals(-2315.0049, rmi.multiply(operand1, operand2));
	}

	@Test
	void divideTest() throws RemoteException
	{
		setUp();
		assertEquals(-0.22004, rmi.divide(operand1, operand2));
	}
	
	@Test
	void validateTest() throws RemoteException
	{
		setUp();
		assertTrue(rmi.valid(equation));
	}
	
	@Test
	void operand1Test() throws RemoteException
	{
		setUp();
		assertEquals(-22.57, rmi.operand1(equation));
	}
	
	@Test
	void operand2Test() throws RemoteException
	{
		setUp();
		assertEquals(102.57, rmi.operand2(equation));
	}
	
	@Test
	void operatorTest() throws RemoteException
	{
		setUp();
		assertEquals('+', rmi.operator(equation));
		
		String test = "+312.23-+123.21";
		assertEquals('-', rmi.operator(test));		
		
		String test2 = "-133.21/-123.32";
		assertEquals('/', rmi.operator(test2));		

	}
	
}
