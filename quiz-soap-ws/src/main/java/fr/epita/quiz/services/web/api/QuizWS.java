package fr.epita.quiz.services.web.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

import fr.epita.quiz.services.web.api.transport.QuestionMessage;

@WebService
public interface QuizWS {

	@WebMethod
	public void saveQuestion(QuestionMessage qm);
}
