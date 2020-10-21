package com.blazdemsar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blazdemsar.domain.QuestionAnswer;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {
	
	@Query(value="SELECT * FROM travelgig.questionanswer WHERE hotelId=:hotelId AND answer IS NOT NULL", nativeQuery=true)
	public List<QuestionAnswer> findByHotelId(int hotelId);
}
