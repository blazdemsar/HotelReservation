package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.QuestionAnswer;

public interface QuestionAnswerService {
	
	public QuestionAnswer save(QuestionAnswer questionAnswer);
	public QuestionAnswer findById(int qaId);
	public List<QuestionAnswer> findAll();
	public void deleteById(int qaId);
	public QuestionAnswer updateById(int qaId, String answer);
	public List<QuestionAnswer> findByHotelId(int hotelId);
	
}
