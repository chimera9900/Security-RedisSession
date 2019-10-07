package com.developer.storesws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	NamedParameterJdbcTemplate jdbc;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public void addUser(User user) {
		String sql1 = "insert into users (username, password, enabled) values (:username, :password, 1)";
		String sql2 = "insert into user_roles (username, role) values(:username, :role)";
		
		jdbc.update(sql1, new MapSqlParameterSource("username", user.getUsername())
				.addValue("password", bCryptPasswordEncoder.encode(user.getPassword())));
		

		jdbc.batchUpdate(sql2, (SqlParameterSource[]) user.getAuthorities().stream()
				.map(x -> new MapSqlParameterSource("username", user.getUsername())
						.addValue("role", x.getAuthority()))
				.toArray(size -> new SqlParameterSource[size]));
		
		
	}

}
