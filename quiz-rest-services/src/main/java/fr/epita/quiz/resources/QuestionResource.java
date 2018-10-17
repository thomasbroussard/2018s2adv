package fr.epita.quiz.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.QuizDataservice;
import fr.epita.quiz.services.web.api.transport.QuestionMessage;

@Path("questions")
public class QuestionResource {
	

	@Inject
	QuizDataservice ds;
	
	@POST
	@Path("/")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public void createQuestion(QuestionMessage message) {
		Question question = message.toQuestion();
		ds.createQuestionWithChoices(question, new MCQChoice());
	}
	
	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<QuestionMessage> findAllQuestions(
			@QueryParam("s")
			String inputString) {
		
		List<QuestionMessage> messages = new ArrayList<QuestionMessage>();
		Question question = new Question();
		question.setQuestionLabel(inputString);
		Map<Question, List<MCQChoice>> map = ds.findAllQuestions(question);
		for(Entry<Question,List<MCQChoice>> entry : map.entrySet()) {
			QuestionMessage qm = new QuestionMessage();
			qm.setQuestionLabel(entry.getKey().getQuestionLabel());
			messages.add(qm);
		}
		
		
		
		return messages;
	}
	
}