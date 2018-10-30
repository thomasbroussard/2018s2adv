package fr.epita.iam.services.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.epita.iam.Identity;

@Repository
public class IdentityDAO extends GenericDAO<Identity> {

	@Override
	public List<Identity> search(Identity criteriaInstance) {
		String hql = "from Identity as i where  i.lastName like :pLastName or i.firstName like :pFirstName";
		TypedQuery<Identity> query = this.em.createQuery(hql, Identity.class);
		query.setParameter("pLastName", "%" + criteriaInstance.getLastName()+ "%");
		query.setParameter("pFirstName", "%" + criteriaInstance.getFirstName()+ "%");
		return	query.getResultList();
	}

	@Override
	public Class<Identity> getType() {
		return Identity.class;
	}

}
