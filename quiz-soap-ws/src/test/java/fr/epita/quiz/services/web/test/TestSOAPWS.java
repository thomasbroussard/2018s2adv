package fr.epita.quiz.services.web.test;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.services.web.QuizWSImpl;
import fr.epita.quiz.services.web.api.QuizWS;
import fr.epita.quiz.services.web.api.transport.MCQChoiceMessage;
import fr.epita.quiz.services.web.api.transport.QuestionMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/applicationContext.xml"})
public class TestSOAPWS {
	
	private static final Logger LOGGER = LogManager.getLogger(TestSOAPWS.class);
	
	@Inject
	QuizWS quizWSClient;
	
	
	@Test
	public void test() {
		QuestionMessage qm = new QuestionMessage();
		qm.setQuestionLabel("what is hibernate?");
		MCQChoiceMessage message = new MCQChoiceMessage();
		message.setLabel("a JPA provider");
		qm.setMcqChoiceList(Arrays.asList(message));
		LOGGER.info("about to trigger save method with : {} ",qm);
		
		quizWSClient.saveQuestion(qm);
	}
	

}
