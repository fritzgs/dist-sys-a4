package a5_rmi_fritz20071968;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;


class Test {

	@org.junit.jupiter.api.Test
	void test() throws RemoteException {
		RMI rmi = new RMI();
		String a ="84.45+-3.45";
		System.out.println(rmi.operand2(a));
		System.out.println();

		System.out.println(rmi.operand1(a));

//		assertEquals(rmi.operand1(a), 122.0);
	}

}
