package com.blazdemsar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int qaId;
	
	private int hotelId;
	
	private String question;
	
	private String answer;
	
	public QuestionAnswer () {
		super();
	}

	public QuestionAnswer(int qaId, int hotelId, String question, String answer) {
		super();
		this.qaId = qaId;
		this.hotelId = hotelId;
		this.question = question;
		this.answer = answer;
	}

	public QuestionAnswer(int qaId, int hotelId, String question) {
		super();
		this.qaId = qaId;
		this.hotelId = hotelId;
		this.question = question;
	}

	public int getQaId() {
		return qaId;
	}

	public void setQaId(int qaId) {
		this.qaId = qaId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "QuestionAnswer [qaId=" + qaId + ", hotelId=" + hotelId + ", question=" + question + ", answer=" + answer
				+ "]";
	}
	
}
