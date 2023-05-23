//package com.patient.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.patient.Entity.JwtRequest;
//import com.patient.Entity.JwtResponse;
//import com.patient.Security.CustomUserDetailService;
//import com.patient.Security.JwtUtilTokenHelper;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@RestController
//@CrossOrigin
//public class AuthController {
//
//	@Autowired
//	private CustomUserDetailService customUserDetailService;
//
////	@Autowired
////	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtUtilTokenHelper jwtUtilTokenHelper;
//
//	@PostMapping("/login")
//	public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//		try {
////			this.authenticationManager.authenticate(
////					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//		} catch (UsernameNotFoundException e) {
//			e.printStackTrace();
//			throw new Exception("User Not Found Exception");
//		} catch (BadCredentialsException e) {
//			e.printStackTrace();
//			throw new Exception("Bad Credential");
//		}
//
//		UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
//
//		String token = this.jwtUtilTokenHelper.generateToken(userDetails);
//		// {"token":"value"}
//		System.out.println(token);
//
//		return ResponseEntity.ok(new JwtResponse(token));
//
//	}
//	
//	@PostMapping("/logout")
//	public String logoutDo(HttpServletRequest request,HttpServletResponse response) {
//		return "redirect/login";
//	}
//
//}
