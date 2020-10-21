package com.blazdemsar.restclient;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HotelRestClient {
	
	public JSONObject saveBooking(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Booking received ............."+ jsonObject);

		String uri = "http://localhost:8484";

		// Prepare the request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		// Creating a RestTemplate object to post JSONObject to microservice
		RestTemplate restTemplate = new RestTemplate();
		//Sending the request to your microservice.
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(uri +"/saveBooking", request, Object.class);
		
		System.out.println("responseEntity: " + responseEntity.getBody());
		
		//Prepare a JSONObject response object
		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.putOpt("booking", responseEntity.getBody());
		
		return jsonResponseObject;
	}

	public JSONObject getHotelName(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Hotel Name received ............."+ jsonObject.get("hotelKey").toString());

		String uri = "http://localhost:8383";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri +"/getHotelName", request, Object[].class);
		Object[] listHotelObject = responseEntity.getBody();

		JSONArray jsonArray = new JSONArray();

		for(Object obj : listHotelObject){
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(obj));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listHotel", jsonArray);

		return jsonResponseObject;

	}

	public JSONObject getHotelRooms(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Hotel Name received ............."+ jsonObject.get("hotelId").toString());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8383";

		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri+"/getHotelRooms", request, Object[].class);

		Object[] hotelRooms = responseEntity.getBody();

		System.out.println(hotelRooms);

		JSONArray jsonArray = new JSONArray();

		for (Object o : hotelRooms) {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(o));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listHotelRooms", jsonArray);

		System.out.println(jsonResponseObject);

		return jsonResponseObject;

	}
	
	public JSONObject getHotelRoomsAvailable(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Hotel Room Details received .............");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8383";

		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri+"/getHotelRoomsAvailable", request, Object[].class);

		Object[] hotelRoomsAvailable = responseEntity.getBody();

		System.out.println(hotelRoomsAvailable);

		JSONArray jsonArray = new JSONArray();

		for (Object o : hotelRoomsAvailable) {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(o));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listHotelRoomsAvailable", jsonArray);

		System.out.println(jsonResponseObject);

		return jsonResponseObject;

	}
	
	public JSONObject getBookingsByUserId(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Booking details received ............." + jsonObject);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
		
		//System.out.println(request);
		
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8484";

		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri+"/getBookingsByUserId", request, Object[].class);

		Object[] bookings = responseEntity.getBody();

		System.out.println(bookings);

		JSONArray jsonArray = new JSONArray();

		for (Object o : bookings) {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(o));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listOfBookings", jsonArray);

		System.out.println("Printing response from microservice: " + jsonResponseObject);

		return jsonResponseObject;

	}
	
	public JSONObject updateBookingStatus(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Booking details received ............."+ jsonObject);

		String uri = "http://localhost:8484";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(uri +"/updateBookingStatus", request, Object.class);
		
		System.out.println("responseEntity: " + responseEntity.getBody());
		
		//Prepare a JSONObject response object
		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.putOpt("booking", responseEntity.getBody());
		
		return jsonResponseObject;
	}
	
	public JSONObject saveReview(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Hotel review received ............."+ jsonObject);

		String uri = "http://localhost:8383";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(uri +"/saveReview", request, Object.class);
		
		System.out.println("responseEntity: " + responseEntity.getBody());
		
		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.putOpt("hotelReview", responseEntity.getBody());
		
		return jsonResponseObject;
	}
	
	public JSONObject getReviewsForHotel(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Review details received .............");

		String uri = "http://localhost:8383";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri +"/getReviewsForHotel", request, Object[].class);
		Object[] listHotelReviewsObject = responseEntity.getBody();

		JSONArray jsonArray = new JSONArray();

		for(Object obj : listHotelReviewsObject){
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(obj));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listOfReviews", jsonArray);

		return jsonResponseObject;

	}
	
	public JSONObject getHotelQA(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("Q&A details received .............");

		String uri = "http://localhost:8383";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri +"/getHotelQA", request, Object[].class);
		Object[] listHotelQAObject = responseEntity.getBody();

		JSONArray jsonArray = new JSONArray();

		for(Object obj : listHotelQAObject){
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(obj));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listOfHotelQA", jsonArray);

		return jsonResponseObject;

	}
	
	public JSONObject saveQuestionAnswer(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("QA details received ............."+ jsonObject);

		String uri = "http://localhost:8383";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(uri +"/saveQuestionAnswer", request, Object.class);
		
		System.out.println("responseEntity: " + responseEntity.getBody());
		
		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.putOpt("questionAnswer", responseEntity.getBody());
		
		return jsonResponseObject;
	}
	
	public JSONObject getAllQAs() throws JSONException, JsonProcessingException {

		System.out.println("Request for QAs received .............");

		String uri = "http://localhost:8383";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri +"/getAllQAs", request, Object[].class);
		Object[] listQAObject = responseEntity.getBody();

		JSONArray jsonArray = new JSONArray();

		for(Object obj : listQAObject){
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(obj));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listOfAllQAs", jsonArray);

		return jsonResponseObject;

	}
	
	public JSONObject updateQA(JSONObject jsonObject) throws JSONException, JsonProcessingException {

		System.out.println("QA details received ............."+ jsonObject);

		String uri = "http://localhost:8383";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(uri +"/saveQuestionAnswer", request, Object.class);
		
		System.out.println("responseEntity: " + responseEntity.getBody());
		
		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.putOpt("questionAnswer", responseEntity.getBody());
		
		return jsonResponseObject;
	}

}
