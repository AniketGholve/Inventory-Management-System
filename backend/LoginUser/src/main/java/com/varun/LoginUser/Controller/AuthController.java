package com.varun.LoginUser.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.varun.LoginUser.Entity.Request;
import com.varun.LoginUser.Entity.Response;
import com.varun.LoginUser.Security.CustomUserDetailService;
import com.varun.LoginUser.Security.JwtUtilTokenHelper;

@Controller
public class AuthController {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtilTokenHelper jwtUtilTokenHelper;

	@PostMapping("/login")
	public ResponseEntity<Response> createToken(@RequestBody Request jwtRequest) throws Exception {
		System.out.println("qqqqqqqqqqqqq");
		System.out.println(jwtRequest);
		try {

			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not Found Exception");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential");
		}

		UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtilTokenHelper.generateToken(userDetails);
		System.out.println("asdfghjkl");
		System.out.println("JWT" + token);

		// {"token":"value"}

		return ResponseEntity.ok(new Response(token));

	}

}
