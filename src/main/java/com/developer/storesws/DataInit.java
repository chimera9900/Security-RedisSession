package com.developer.storesws;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.developer.storesws.model.Store;
import com.developer.storesws.service.StoreService;
import com.developer.storesws.service.UserService;

@Component
public class DataInit implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(DataInit.class);
	private static volatile boolean initialized = false;

	@Autowired
	StoreService storeService;

	@Autowired
	UserService userService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(initialized) return;
		initialized = true;
	
		storeService.addStore(new Store("http://google.com", "good website"));
		storeService.addStore(new Store("http://yahoo.com", "wonderful website"));
		
		userService.addUser(new User("admin","admin", Arrays.asList(
				new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("ROLE_ADMIN")
				)));
		userService.addUser(new User("user", "user", Arrays.asList(
				new SimpleGrantedAuthority("ROLE_USER")
				)));
		
	}

}
