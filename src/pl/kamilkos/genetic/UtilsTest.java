package pl.kamilkos.genetic;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void parseBinaryTest1() {
		assertEquals(Utils.parseBinary("000001010"), 10);
	}
	
	@Test
	public void parseBinaryTest2() {
		assertEquals(Utils.parseBinary("11111111"), 255);
	}
	
	@Test
	public void intToBinTest1() {
		assertEquals(Utils.intToBin(10), "1010");
	}
	
	@Test
	public void intToBinTest2() {
		assertEquals(Utils.intToBin(255), "11111111");
	}
	
	@Test
	public void intToBinTest3() {
		int number = (int) (Math.random()*10000);
		assertEquals(Utils.parseBinary(Utils.intToBin(number)), number);
	}
	
	@Test
	public void intToBinTest4() {
		assertEquals(Utils.intToBin(15, 12), "000000001111");
	}
	
	@Test
	public void intToBinTest5() {
		int number = (int) (Math.random()*10000);
		assertEquals(Utils.parseBinary(Utils.intToBin(number, 14)), number);
	}
	
	@Test
	public void intToBinTest6() {
		int number = (int) (Math.random()*10000);
		assertEquals(Utils.parseBinary(Utils.intToBin(number, 21)), number);
	}

}
