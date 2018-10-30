package fr.epita.iam.business.services;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.epita.iam.Identity;
import fr.epita.iam.services.data.AddressDAO;
import fr.epita.iam.services.data.IdentityDAO;

@Repository
public class IdentityManagementService {

	@Inject
	AddressDAO addressDAO;	
	
	@Inject
	IdentityDAO identityDAO;
	
	@Transactional
	public void saveIdentity(Identity identity) {
		this.addressDAO.create(identity.getWorkAddress());
		this.identityDAO.create(identity);
		
	}

	public List<Identity> searchIdentity(Identity criteria) {
		return identityDAO.search(criteria);
	}

}
