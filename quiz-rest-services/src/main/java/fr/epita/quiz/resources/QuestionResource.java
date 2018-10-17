package fr.epita.quiz.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.QuizDataservice;
import fr.epita.quiz.services.web.api.transport.QuestionMessage;

@Path("questions")
public class QuestionResource {


	@Inject
	QuizDataservice ds;
	
	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<QuestionMessage> findAllQuestions() {
		
		List<QuestionMessage> messages = new ArrayList<QuestionMessage>();
		Question question = new Question();
		Map<Question, List<MCQChoice>> map = ds.findAllQuestions(question);
		for(Entry<Question,List<MCQChoice>> entry : map.entrySet()) {
			QuestionMessage qm = new QuestionMessage();
			qm.setQuestionLabel(entry.getKey().getQuestionLabel());
			messages.add(qm);
		}
		
		
		
		return messages;
	}
	
}