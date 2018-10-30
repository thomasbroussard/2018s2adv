package fr.epita.maths.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epita.maths.Factorial;

public class TestFactorial {

	
	@BeforeClass()
	public static void beforeAnyTest() {
		System.out.println("test before class");
	}

	@Before
	public void beforeEveryTest() {
		System.out.println("test before");
	}
	
	@Test
	public void testRegularValue() {
		System.out.println("testRegularValue");
		// given
		int n = 5;

		// when
		int result = Factorial.calculate(n);

		// then
		Assert.assertEquals(120, result);

	}

	@Test
	public void testNegativeValue() {
		System.out.println("testNegativeValue");
		try {
			// given
			int n = -5;

			// when
			Factorial.calculate(n);

		} catch (Exception e) {
			// then
			// TODO log
			e.printStackTrace();
			return;
		}
		Assert.fail("should not be able to reach this");

	}

	@After
	public void after() {
		System.out.println("after");
	}
	@AfterClass
	public static void afterclass() {
		System.out.println("after class");
	}
	
	
}
