package fr.epita.quiz.services.data;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

@Repository
public class QuizDataservice {

	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	MCQChoiceDAO mcqDAO;
	
	@Inject
	SessionFactory sessionFactory;
	
	
	public void createQuestionWithChoices(Question question, MCQChoice... choices) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		questionDAO.create(question);
		for (MCQChoice choice : choices) {
			choice.setQuestion(question);
			mcqDAO.create(choice);
		}
		tx.commit();
		session.close();
		
	}
	
}
