package fr.epita.quiz.services.data;

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
		Commitable<Transaction> commitableTx = getTransaction(session);
		session.save(question);
		commitableTx.commit();

	}


	private Commitable<Transaction> getTransaction(Session session) {
		final boolean areWeTheInitiatorOfTheTransaction =  session.getTransaction() == null 
				|| ! session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE);
		final Transaction currentTransaction = areWeTheInitiatorOfTheTransaction ? session.beginTransaction() : session.getTransaction();
		
		
		return new Commitable<Transaction>() {
			
			public boolean isCommitable() {
				return areWeTheInitiatorOfTheTransaction;
			}
			
			
			public Transaction getInstance() {
				return currentTransaction;
			}


			public void commit() {
				if (areWeTheInitiatorOfTheTransaction) {
					currentTransaction.commit();
				}
				
			}
		};
	
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
		Commitable<Transaction> tx = getTransaction(session);
		session.update(question);
		tx.commit();
	}

	public void delete(Question question) {
		Session session = getSession();
		Commitable<Transaction> tx = getTransaction(session);
		session.delete(question);
		tx.commit();
	}

}
