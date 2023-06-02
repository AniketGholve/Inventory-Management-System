package com.patient.Config;

import org.keycloak.OAuth2Constants;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
        http.csrf().disable().authorizeHttpRequests()
            .requestMatchers(new AntPathRequestMatcher("/addUser")).permitAll().anyRequest().permitAll();
        
        
        http.oauth2Login()
            .and()
            .logout()
            .addLogoutHandler(keycloakLogoutHandler)
            .logoutSuccessUrl("/");
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
	
//	@Bean
//	public Keycloak keycloak() {
//		return KeycloakBuilder.builder()
//				.serverUrl("http://localhost:8080/realms/InventoryManagementSystem")
//				.realm("InventoryManagementSystem")
//				.clientId("Keycloak-SpringBoot")
//				.build();
//	}
    
    
    @Bean
	public Keycloak getAdminKeycloakUser() {
    	System.out.println("lkjhgfdsawertyuiopjhgfd");
	    return KeycloakBuilder.builder().serverUrl("http://localhost:8080")
	            .grantType(OAuth2Constants.PASSWORD).realm("Inventory_Management_New")
	            .username("rishabh")
	            .password("rishabh").clientSecret("Sr8qlf4GXRmvbCTlvhpVYB5bfuqYOJRb").clientId("my_client_new").build();
	    //827252d5-a365-4f65-af59-3f840d6d702d
	    //void com.fasterxml.jackson.jaxrs.cfg.AnnotationBundleKey

	    
	}
    
    
    
    
    


}