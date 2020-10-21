package com.blazdemsar.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.QuestionAnswer;
import com.blazdemsar.service.QuestionAnswerService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class QuestionAnswerController {
	
	@Autowired
	QuestionAnswerService questionAnswerService;
	
	@RequestMapping(value="/saveQuestionAnswer", method=RequestMethod.POST)
	public ResponseEntity<?> saveQuestionAnswer(@RequestBody QuestionAnswer questionAnswer) {
		
		System.out.println("Inside of QuestionAnswerController.saveQuestionAnswer()....." + questionAnswer.toString());
		
		QuestionAnswer qaFromDb = questionAnswerService.save(questionAnswer);
		System.out.println(qaFromDb);
		
		if (qaFromDb != null) {
			
			return new ResponseEntity<QuestionAnswer>(qaFromDb, HttpStatus.OK);
		
		} else {
			
			return new ResponseEntity<String>("Hotel Microservice could not save the Q&A!", HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	@RequestMapping(value="/getHotelQA", method=RequestMethod.POST)
	public ResponseEntity<List<QuestionAnswer>> getHotelQA(@RequestBody String questionAnswer) throws JSONException, JsonProcessingException {
		
		System.out.println("In Microservice........." + questionAnswer);
		
		JSONObject jsonQA = new JSONObject(questionAnswer);
		
		QuestionAnswer questionAns = new QuestionAnswer();
		questionAns.setHotelId(jsonQA.getInt("hotelId"));
		
		System.out.println("questionAns....."+questionAns.getHotelId());
		
		List<QuestionAnswer> resultList = questionAnswerService.findByHotelId(questionAns.getHotelId());
		System.out.println(resultList);
		
		return new  ResponseEntity<List<QuestionAnswer>>(resultList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllQAs", method=RequestMethod.POST)
	public ResponseEntity<List<QuestionAnswer>> getAllQAs() throws JSONException, JsonProcessingException {
		
		System.out.println("In Microservice.........");
		
		List<QuestionAnswer> resultList = questionAnswerService.findAll();
		System.out.println(resultList);
		
		return new  ResponseEntity<List<QuestionAnswer>>(resultList, HttpStatus.OK);
	}
	
}
