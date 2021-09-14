package com.saraya.surrveyProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.saraya.surrveyProject.model.Question;
import com.saraya.surrveyProject.service.SurveyService;

@RestController
public class SurveyController {
	@Autowired
	private SurveyService ss;
	
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> questionBySurvey(@PathVariable 
			String surveyId){
		return ss.retrieveQuestions(surveyId);
	}
}
