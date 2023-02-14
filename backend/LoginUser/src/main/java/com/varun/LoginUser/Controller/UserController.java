package com.varun.LoginUser.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varun.LoginUser.Entity.UserEntity;
import com.varun.LoginUser.Repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/addUser")
	public String addUser(@RequestBody UserEntity user) {
		String role = user.getRole();
		String arr[] = { "CLP", "ELP", "ALP", "MLP" };
		boolean result = Arrays.asList(arr).contains(role);
		if (result) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return "User data added sucessfully";
		}
		return "wrong role";
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		UserEntity user = userRepository.findByUsername(request.getUsername());

		if (user == null) {
			// return new LoginResponse(false, "Incorrect username");
			return "1";
		} else if (!user.getPassword().equals(request.getPassword())) {
			// return new LoginResponse(false, "Incorrect password");
			return "1";
		}
		// return new LoginResponse(true, "Login successful", user.getRole());
		return "2";
	}

	@GetMapping("/get")
	public String get() {
		// return userRepository.findAll();
		return "Loged in";
	}

	@GetMapping("/get/{username}")
	public UserEntity getRole(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}
}

class LoginRequest {
	private String username;
	private String password;

	LoginRequest() {
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

class LoginResponse {
	private boolean success;
	private String message;
	private String role;

	public LoginResponse(boolean success, String message, String role) {
		this.success = success;
		this.message = message;
		this.role = role;
	}

	public LoginResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getRole() {
		return role;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
