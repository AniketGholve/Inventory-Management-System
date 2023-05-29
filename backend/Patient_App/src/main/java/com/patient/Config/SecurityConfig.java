package com.patient.Config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

import org.springframework.security.core.session.SessionRegistryImpl;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;

import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

//package com.patient.Config;

@Configuration

@EnableWebSecurity

class SecurityConfig {

	private final KeycloakLogoutHandler keycloakLogoutHandler;

	SecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {

		this.keycloakLogoutHandler = keycloakLogoutHandler;

	}

	@Bean

	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {

		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());

	}

	@Bean

	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()

				.requestMatchers("/customers*")

				.hasRole("USER")

				.anyRequest().authenticated();


		http.oauth2Login()

				.and()

				.logout()

				.addLogoutHandler(keycloakLogoutHandler)

				.logoutSuccessUrl("/");

		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		return http.build();

	}
	
	@Bean
	public Keycloak keycloak() {
		return KeycloakBuilder.builder()
				.serverUrl("http://localhost:8080/realms/InventoryManagementSystem")
				.realm("InventoryManagementSystem")
				.clientId("Keycloak-SpringBoot")
				.build();
	}

}