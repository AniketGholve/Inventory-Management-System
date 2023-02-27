package com.patient.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.patient.Security.CustomUserDetailService;
import com.patient.Security.JwtAuthenticationEntryPoint;
import com.patient.Security.JwtRequestFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests()

<<<<<<< HEAD
				.requestMatchers(new AntPathRequestMatcher("/login"),new AntPathRequestMatcher("/api/SuccessOrders/1"), new AntPathRequestMatcher("/api/ErrorOrders/1"),new AntPathRequestMatcher("/updateEnterprise"), new AntPathRequestMatcher("/createEnterprises"),new AntPathRequestMatcher("/api/addUser"))
=======
<<<<<<< HEAD
				.requestMatchers(new AntPathRequestMatcher("/login"),new AntPathRequestMatcher("/api/SuccessOrders/1"), new AntPathRequestMatcher("/api/ErrorOrders/1"), new AntPathRequestMatcher("/api/addUser"), new AntPathRequestMatcher("/getAllEnterprise"))
=======
				.requestMatchers(new AntPathRequestMatcher("/login"),new AntPathRequestMatcher("/createEnterprises"), new AntPathRequestMatcher("/api/ErrorOrders/1"), new AntPathRequestMatcher("/api/addUser"))
>>>>>>> 7d8be012ab4aa0a55369f2105e900dceed9e8455
>>>>>>> 6b2a4b70171681aa532867ae9265394b4d43d133
				.permitAll().anyRequest().authenticated().and().cors().and().exceptionHandling()
				.authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();

		return defaultSecurityFilterChain;

	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
