package com.patient.ServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.http.auth.Credentials;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.patient.Config.Credentials;
//import com.patient.Config.KeycloakConfig;
import com.patient.Entity.UserEntity;
import com.patient.Repo.UserEntityRepo;
import com.patient.Service.UserEntityService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class UserEntityServiceImpl implements UserEntityService {
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UserEntityRepo userEntityRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private KeycloakConfig keycloakConfig;
	@Autowired
	private Keycloak keycloak;

	
		
//	@Autowired
//	public UserEntityServiceImpl(Keycloak keycloak) {
//		this.keycloak=keycloak;
//	}
//	
	

	@Override
	public UserEntity findByCustomUsername(String username) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select id,password,role,username,dateof_birth,first_name,last_name,phone_no from user_entity where username=?");
		q.setParameter(1, username);
		UserEntity u=(UserEntity) q.getSingleResult();
		return u;
	}

	@Override
	public Integer addUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		System.out.println(userEntity.toString());
		String role = userEntity.getRole();
		String password = userEntity.getPassword();
		String arr[] = { "CLP", "ELP", "ALP", "MLP" };
		boolean result = Arrays.asList(arr).contains(role);
		if (result) {
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			System.out.println("lkjhg");
			userEntity.setPassword(userEntity.getPassword());
			System.out.println("lkjhg");
			userEntity.setActive(true);
			userEntity.setDeleted(false);
			System.out.println("lkjhg");
			userEntityRepo.save(userEntity);
			System.out.println("kjhg99fd");

			
			
			//System.out.println(keycloak.realm("Inventory_Management").getRealmEventsConfig());
//			List<UserRepresentation> users = keycloak.realm("Inventory_Management_New")
//				      .users()
//				      .searchByUsername("ajay", false);
//			for(UserRepresentation u:users)
//			{
//				System.out.println(u.getEmail());
//			}

//			System.out.println(keycloak.toString());
			//create user in keycloak
			UserRepresentation userRepresentation = new UserRepresentation();
			userRepresentation.setUsername(userEntity.getUsername());
			//Response response = keycloak.realm(password)

			CredentialRepresentation credential = new CredentialRepresentation();
			credential.setType(CredentialRepresentation.PASSWORD);
			credential.setValue(password);
			credential.setTemporary(false);
			userRepresentation.setCredentials(Arrays.asList(credential));
			userRepresentation.setEnabled(true);
			
			System.out.println("09876543");
			
			keycloak.realm("InventoryManagementSystem").users().create(userRepresentation);
			
			RealmResource realmResource = keycloak.realm("InventoryManagementSystem");
			UsersResource usersResource = realmResource.users();
			List<UserRepresentation> users = usersResource.search(userEntity.getUsername());
			UserResource userResource = usersResource.get(users.get(0).getId());
			RoleRepresentation roleRepresentation = realmResource.roles().get(role).toRepresentation();
			userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
			//Response response = keycloak.realm("Inventory_Management_New").realmResource.users().create(userRepresentation);


//			keycloak.realm("Inventory_Management_New").users().create(userRepresentation);		
//			RoleRepresentation roleRepresentation = keycloak.realm("Inventory_Management_New").roles().get(role).toRepresentation();
//			System.out.println("mnbvcx");
//			keycloak.realm("Inventory_Management_New").users().get(userRepresentation.getUsername()).roles().realmLevel().add(Arrays.asList(roleRepresentation));
//			System.out.println("----------");
//			keycloak.realm("Inventory_Management_New").users().create(userRepresentation);
			//-------==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//			CredentialRepresentation credential = Credentials
//		            .createPasswordCredentials(userEntity.getPassword());
//		    UserRepresentation user = new UserRepresentation();
//		    user.setUsername(userEntity.getUsername());
//		    user.setFirstName(userEntity.getFirstName());
//		    user.setLastName(userEntity.getLastName());
//		    user.setCredentials(Collections.singletonList(credential));
//		    user.setEnabled(true);

//		    UsersResource instance =  keycloakConfig.getInstance();
//		    instance.create(user);

			
			return 1;
			
			
		}
		return -1;
		
	}

	@Override
	public UserEntity editUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		System.out.println("user");
		System.out.println(userEntity.toString());
		
		UserEntity u = userEntityRepo.findByUsername(userEntity.getUsername());
		//UserEntity u=userEntityServiceImpl.findByCustomUsername(user.getUsername());
		System.out.println(u.toString());
		if(userEntity.getFirstName() != null) {
			u.setFirstName(userEntity.getFirstName());
			System.out.println(userEntity.getFirstName());	
		}
		if (userEntity.getLastName()!= null) {
			u.setLastName(userEntity.getLastName());
		}
		if (userEntity.getDateofBirth() != null) {
			u.setDateofBirth(userEntity.getDateofBirth());
		}
		if (userEntity.getPhoneNo() != null) {
			u.setPhoneNo(userEntity.getPhoneNo());
		}
		if (userEntity.getPassword() != null) {
//			u.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			u.setPassword(userEntity.getPassword());

		}
		if (userEntity.getUsername() != null) {
			u.setUsername(userEntity.getUsername());
		}
		if (userEntity.getRole() != null) {
			u.setRole(userEntity.getRole());
		}
		
		userEntityRepo.save(u);
		return u;
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userEntityRepo.deleteById(id);	
	}

	@Override
	public UserEntity findUserById(int id) {
		// TODO Auto-generated method stub
		UserEntity u = userEntityRepo.findById(id).orElseThrow();
		return u;
	}

}
