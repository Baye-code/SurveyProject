package com.saraya.surrveyProject.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saraya.surrveyProject.model.Question;
import com.saraya.surrveyProject.model.Survey;
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
	
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question SingleQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		return ss.retrieveASingleQuestion(surveyId, questionId);
	}
	
	@GetMapping("/surveys")
	public List<Survey> ExistingSurveys() {
		return ss.retrieveAllSurvey();
	}
	@GetMapping("/surveys/{surveyId}/questions/{questionId}/options")
	public List<String> optionsByQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		return ss.retrieveASingleQuestion(surveyId,questionId).getOptions();
	}
	
	@PostMapping("/surveys/{surveyId}/questions")
    ResponseEntity<Void> add(@PathVariable String surveyId,
            @RequestBody Question question) {

        Question createdTodo = ss.addQuestion(surveyId, question);

        if (createdTodo == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
	@DeleteMapping("/surveys/{surveyId}/questions/{questionId}")
	boolean delete(@PathVariable String surveyId, @PathVariable String questionId ){
		
		boolean result=ss.deleteQuestion(surveyId, questionId);
		
		
		return result;
		
	}
//	@PutMapping("/surveys/{surveyId}/questions/{questionId}")
//	boolean update(@PathVariable String surveyId, @PathVariable String questionId, @RequestBody String correctAnswer,@RequestBody List<String> options ){
//		
//		boolean result=ss.updateQuestion(surveyId, questionId, correctAnswer,options);
//		
//		return result;
//	}
	@PutMapping("/surveys/{surveyId}/questions/")
	
	boolean update(@PathVariable String surveyId, @RequestBody Question question ){
		
		
		
		return ss.updateQuestion(surveyId, question);
	}
}
