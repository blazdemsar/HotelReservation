package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.QuestionAnswer;
import com.blazdemsar.repository.QuestionAnswerRepository;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
	
	@Autowired
	QuestionAnswerRepository questionAnswerRepository;
	
	@Override
	public QuestionAnswer save(QuestionAnswer questionAnswer) {
		return questionAnswerRepository.save(questionAnswer);
	}

	@Override
	public QuestionAnswer findById(int qaId) {
		
		Optional<QuestionAnswer> optQA = questionAnswerRepository.findById(qaId);
		
		if (optQA.isPresent()) {
			return optQA.get();
		}
		
		return null;
	}

	@Override
	public List<QuestionAnswer> findAll() {
		return questionAnswerRepository.findAll();
	}

	@Override
	public void deleteById(int qaId) {
		questionAnswerRepository.deleteById(qaId);
	}

	@Override
	public QuestionAnswer updateById(int qaId, String answer) {
		
		QuestionAnswer questionAnswer = findById(qaId);
		
		if (questionAnswer != null) {
			questionAnswer.setAnswer(answer);
			
			return save(questionAnswer);
		}
		
		return null;
	}

	@Override
	public List<QuestionAnswer> findByHotelId(int hotelId) {
		return questionAnswerRepository.findByHotelId(hotelId);
	}

}
