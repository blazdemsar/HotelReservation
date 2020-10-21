package com.blazdemsar.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmployeeController {
	@RequestMapping(value = "/editEmp", method = RequestMethod.POST)
	public ResponseEntity<String> editEmp(@RequestBody String emp) {
		
		JSONObject jsonObject=new JSONObject(emp);
		System.out.println("Inside TravelGig Controller"+jsonObject.getString("empName"));
		System.out.println("Inside TravelGig Controller"+jsonObject.getString("empId"));
		System.out.println("Inside TravelGig Controller"+jsonObject.getString("empAge"));
		System.out.println("Inside TravelGig Controller"+jsonObject.getString("empGender"));
		String successString= "{'success':'success'}";
		JSONObject jsonResponse=new JSONObject(successString);
		return new ResponseEntity<String>(jsonResponse.toString(),HttpStatus.OK);
		
	}
}
