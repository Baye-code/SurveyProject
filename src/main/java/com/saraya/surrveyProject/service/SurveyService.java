package com.saraya.surrveyProject.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saraya.surrveyProject.model.Question;
import com.saraya.surrveyProject.model.Survey;

@Service
public class SurveyService {
	
	private static List<Survey> surveys = new ArrayList<>();
	
	static {
		Question question1 = new Question("Question1",
				"Largest country in the world", "Russia",
				Arrays.asList("India","USA","Russia","China"));
		
		Question question2 = new Question("Question2",
				"Most populus Country", "China",
				Arrays.asList("India","USA","Russia","China"));
		
		Question question3 = new Question("Question3",
				"Highest GOP in the world", "USA",
				Arrays.asList("India","USA","Russia","China"));
		
		Question question4 = new Question("Question4",
				"Second largest english speaking", "India",
				Arrays.asList("India","USA","Russia","China"));
		
		
		List<Question> questions = new ArrayList<>(Arrays
				.asList(question1, question2, question3, question4));
		
		Survey survey = new Survey("Survey1", "The World survey",
				"Survey about the world", questions);
		
		surveys.add(survey);
	}
	static {
		Question question5 = new Question("Question1",
				"Fisrt Man in the Earth", "Adam",
				Arrays.asList("Neandertal","Lucie","Adam","Me"));
		
		Question question6 = new Question("Question2",
				"First programming language in 2020","Python",
				Arrays.asList("Python","C++","Java","Ruby"));
		
		Question question7 = new Question("Question3",
				"Best education system in the world", "Finlande",
				Arrays.asList("Finlande","USA","Singapour","China"));
		
		Question question8 = new Question("Question4",
				"Second largest english speaking", "India",
				Arrays.asList("India","USA","Russia","China"));
		
		
		List<Question> questions2 = new ArrayList<>(Arrays
				.asList(question5, question6, question7, question8));
		
		Survey survey2 = new Survey("Survey2", "The World survey",
				"Survey about the world", questions2);
		
		surveys.add(survey2);
	}
	
	public List<Survey> retrieveAllSurvey(){
		return surveys;
	}
	
	public Survey retrieveSurveyById(String surveyId) {
		for(Survey survey : surveys) {
			if(survey.getId().equals(surveyId)) {
				return survey;
			}
		}
		return null;
	}
	
	public List<Question> retrieveQuestions(String surveyId){
		return retrieveSurveyById(surveyId).getQuestions();
	}
	
	public Question retrieveASingleQuestion(String surveyId, String questionId) {
		for(Question question : retrieveQuestions(surveyId)) {
			if(question.getId().equals(questionId)) {
				return question;
			}
		}
		return null;
	}
	// Add functionnality
	private SecureRandom random = new SecureRandom();

	public Question addQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurveyById(surveyId);

		if (survey == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		question.setId(randomId);

		survey.getQuestions().add(question);

		return question;
	}
	public Question findByID(String surveyId,String questionId) {
		if(surveyId==null) {
			return null;
		}
		for(Question question:retrieveQuestions(surveyId)) {
			if(question.getId().equalsIgnoreCase(questionId)) {
				return question;
			}
		}
		return null;
	}
	public boolean deleteQuestion(String surveyId, String questionId) {
		
		return retrieveQuestions(surveyId).remove(findByID(surveyId,questionId));
	}
	public boolean updateInsideQuestion(String surveyId,String questionId,String description,  String correctAnswer,List<String> options) {
		Question qt=findByID(surveyId,questionId);
		if(qt.equals(null)) {
			return false;
		}
		qt.setDescription(description);
		qt.setCorrectAnswer(correctAnswer);
		qt.setOptions(options);
		return true;
	}
	public boolean updateQuestion(String surveyId, Question question) {
		Question qt = findByID(surveyId, question.getId());
		if(qt==null) {
			return false;
		}
		
		qt.setCorrectAnswer(question.getCorrectAnswer());
		qt.setDescription(question.getDescription());
		qt.setOptions(question.getOptions());
		return true;
		
	}
}
	

