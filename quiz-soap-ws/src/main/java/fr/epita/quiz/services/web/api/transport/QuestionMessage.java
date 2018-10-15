package fr.epita.quiz.services.web.api.transport;

import java.util.ArrayList;
import java.util.List;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

public class QuestionMessage {
	
	private Long id;
	
	private String questionLabel;
	
	private List<MCQChoiceMessage> mcqChoices;

	public String getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestionLabel(String questionLabel) {
		this.questionLabel = questionLabel;
	}

	public List<MCQChoiceMessage> getMcqChoiceList() {
		return mcqChoices;
	}

	public void setMcqChoiceList(List<MCQChoiceMessage> mcqChoices) {
		this.mcqChoices = mcqChoices;
	}
	
	public Question toQuestion() {
		Question question = new Question();
		question.setId(this.id);
		question.setQuestionLabel(this.questionLabel);
		return question;
	}
	public List<MCQChoice> toMCQChoicesList(Question question) {
		List<MCQChoice> choices = new ArrayList<MCQChoice>();
		for (MCQChoiceMessage message : mcqChoices) {
			message.toMCQChoice(question);
		}
		return choices;
	}

	@Override
	public String toString() {
		return "QuestionMessage [id=" + id + ", questionLabel=" + questionLabel + ", mcqChoices=" + mcqChoices + "]";
	}

	
	
}
