package fr.epita.quiz.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.QuestionDAO;
import fr.epita.quiz.services.data.QuizDataservice;
import fr.epita.quiz.services.web.api.transport.MCQChoiceMessage;
import fr.epita.quiz.services.web.api.transport.QuestionMessage;

@Path(QuestionResource.PATH)
public class QuestionResource {
	
	static final String PATH = "questions";

	@Inject
	QuizDataservice ds;
	
	@Inject
	QuestionDAO qDao;
	
	@POST
	@Path("/")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response createQuestion(QuestionMessage message) throws URISyntaxException {
		Question question = toQuestion(message);
		ds.createQuestionWithChoices(question, new MCQChoice());
		return Response.created(new URI(PATH + "/" + String.valueOf(question.getId()))).build();
		
	}
	
	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllQuestions(
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
		
		
		return Response.ok(messages).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response getOneQuestion(
			@PathParam("id")
			String id) {
		
		Question question = qDao.getById(Long.valueOf(id));
		if (question == null) {
			return Response.status(Status.NOT_FOUND).entity("{\"message\" : 'Not found'}").build();
		}
		QuestionMessage message = new QuestionMessage();
		message.setQuestionLabel(question.getQuestionLabel());
	
		
		return Response.ok(message).build();
	}
	
	private static Question toQuestion(QuestionMessage qm) {
		Question question = new Question();
		question.setId(qm.getId());
		question.setQuestionLabel(qm.getQuestionLabel());
		return question;
	}
	private static QuestionMessage fromQuestion(Question question) {
		QuestionMessage questionMessage = new QuestionMessage();
		questionMessage.setId(question.getId());
		questionMessage.setQuestionLabel(question.getQuestionLabel());
		return questionMessage;
	}
	
	//TODO
	private static MCQChoice toMCQChoice(MCQChoiceMessage mcqChoiceMessage) {
		return null;
	}
	
	//TODO
	private static MCQChoiceMessage fromMCQChoice(MCQChoice mcqChoice) {
		return null;
	}
	
	private void addMCQChoiceListToQuestionMessage(List<MCQChoice>list, QuestionMessage qm) {
		List<MCQChoiceMessage> resultList = new ArrayList<>();
		for (MCQChoice choice : list) {
			resultList.add(fromMCQChoice(choice));
		}
		qm.setMcqChoiceList(resultList);
	}
	
}