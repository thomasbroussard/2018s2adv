package fr.epita.maths.test;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/applicationContext.xml"})
public class TestDI {

	@Inject
	String query;
	
	
	@Test
	public void firstDITest() {
		System.out.println(query);
		Assert.assertNotNull(query);
	}
	
}
