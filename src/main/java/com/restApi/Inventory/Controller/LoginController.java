package com.restApi.Inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restApi.Inventory.Service.UserService;
import com.restApi.Inventory.model.User;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/officeInventory")
public class LoginController {
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/signup" ,method=RequestMethod.POST)
	public ResponseEntity<String> userRegistration(@RequestBody User user){
		
		
		String jsonResponse=userService.SaveUserRegistration(user);
		
		
		
		return new ResponseEntity<String>( jsonResponse, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<String> userLogin(@RequestBody User user){
		
		String jsonResponse=userService.userLogin(user);
		
		
		return new ResponseEntity<String>(jsonResponse,HttpStatus.OK);
	}

}
