package com.saraya.surrveyProject.service;

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
	
}
