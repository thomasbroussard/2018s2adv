package fr.epita.quiz.services.web;

import java.util.List;

import javax.inject.Inject;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.QuizDataservice;
import fr.epita.quiz.services.web.api.QuizWS;
import fr.epita.quiz.services.web.api.transport.QuestionMessage;

public class QuizWSImpl implements QuizWS {

	@Inject
	QuizDataservice ds;
	
	@Override
	public void saveQuestion(QuestionMessage qm) {
		
		Question question = qm.toQuestion();
		List<MCQChoice> mcqChoicesList = qm.toMCQChoicesList(question);
		ds.createQuestionWithChoices(question, mcqChoicesList.toArray(new MCQChoice[mcqChoicesList.size()]));
		

	}

}
