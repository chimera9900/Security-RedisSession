package com.developer.storesws.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.developer.storesws.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(username -> userService.findUserByUsername(username));
//		.jdbcAuthentication()
//		.usersByUsernameQuery("select username, password, enabled from users where username = ?")
//		.authoritiesByUsernameQuery("select username, role as authority from user_roles where username = ?")
//		.dataSource(dataSource)
		
		
		;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/actuator/**").permitAll()
		.and()
		.authorizeRequests().anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login")
		.successForwardUrl("/api/stores").permitAll()
		.and()
		.logout().permitAll()
		;
	}

}
