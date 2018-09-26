package fr.epita.maths.test;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/applicationContext.xml"})
public class TestJPA {
	
	@Inject
	private SessionFactory sf;
	
	@Test
	public void testJPA() {
		
		//given 
		Question question = new Question();
		question.setQuestionLabel("What is JPA?");
		
		//when
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(question);
		tx.commit();
		
		session.close();
		
		//then
		Session session2 = sf.openSession();
		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
		
		Assert.assertNotEquals(0, searchQuery.list().size());
		session2.close();
		
	}

}
