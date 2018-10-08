package fr.epita.quiz.services.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import fr.epita.quiz.datamodel.Question;

public class QuestionDAO {

	private static final Logger LOGGER = LogManager.getLogger(QuestionDAO.class);

	@Inject
	private SessionFactory sf;

	public void create(Question question) {
		Session session = getSession();
		Transaction tx = getTransaction(session);
		session.save(question);
		handleCommit(tx);

	}

	private void handleCommit(Transaction tx) {
		// TODO : improve the transaction logic
		tx.commit();
	}

	private Transaction getTransaction(Session session) {
		Transaction currentTransaction = session.getTransaction();
		if (currentTransaction == null 
				|| !currentTransaction.getStatus().equals(TransactionStatus.ACTIVE)){
			currentTransaction = session.beginTransaction();
		}
		
		return currentTransaction;
	}

	private Session getSession() {
		Session session = null;
		try {
			session = sf.getCurrentSession();
		} catch (HibernateException he) {
			LOGGER.warn("got an exception while trying to get the current session", he);
		}
		if (session == null) {
			session = sf.openSession();
		}
		return session;
	}

	public List<Question> search(Question questionCriteria) {
		
		Query<Question> searchQuery = getSession().createQuery("from Question where questionLabel like :inputString ", Question.class);
		searchQuery.setParameter("inputString", "%"+questionCriteria.getQuestionLabel()+"%");
		return searchQuery.list();
		

	}

	public Question getById(Long id) {
		return getSession().get(Question.class, id);
	}

	public void update(Question question) {
		Session session = getSession();
		Transaction tx = getTransaction(session);
		session.update(question);
		handleCommit(tx);
	}

	public void delete(Question question) {
		Session session = getSession();
		Transaction tx = getTransaction(session);
		session.delete(question);
		handleCommit(tx);
	}

}
