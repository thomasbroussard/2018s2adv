package fr.epita.iam.tests;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.iam.Address;
import fr.epita.iam.Identity;
import fr.epita.iam.business.services.IdentityManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContextWithEM.xml" })
public class TestIdentityDataService {

	private static final Logger LOGGER = LogManager.getLogger(TestIdentityDataService.class);
	
	@Inject
	IdentityManagementService ds;

	@Test
	public void test() {
		// given
		Identity identity = new Identity();
		identity.setFirstName("Thomas");

		Address addr = new Address();
		addr.setCountry("France");

		identity.setWorkAddress(addr);

		// when
		ds.saveIdentity(identity);

		// then
		Identity criteria = new Identity();
		criteria.setFirstName("Tho");
		List<Identity> list = ds.searchIdentity(criteria);
		
		LOGGER.info(list);
		
		Assert.assertTrue(list.size() >= 1);

	}
}
