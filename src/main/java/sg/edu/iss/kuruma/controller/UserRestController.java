package sg.edu.iss.kuruma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.kuruma.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	private UserService uservice;
	
	@PostMapping("/user/check_email")
	public String checkDuplicateEmail(@Param("email") String email) {
		return uservice.isEmailUnique(email) ? "OK" : "Duplicate";
	}
	
	
}
