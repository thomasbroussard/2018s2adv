package fr.epita.iam.services.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.epita.iam.Address;

@Repository
public class AddressDAO extends GenericDAO<Address>{

	@Override
	public List<Address> search(Address criteriaInstance) {
		String hql = "from Address as a where  a.country = :pCountry";
		TypedQuery<Address> query = this.em.createQuery(hql, Address.class);
		query.setParameter("pCountry", criteriaInstance.getCountry());
		return	query.getResultList();
	}

	@Override
	public Class<Address> getType() {
		// TODO Auto-generated method stub
		return Address.class;
	}

}
