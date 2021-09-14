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
