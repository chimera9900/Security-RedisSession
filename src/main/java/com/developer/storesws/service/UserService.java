package com.developer.storesws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.developer.storesws.model.MyUser;

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
	
	public MyUser findUserByUsername(String username) {
		try {
			String sql1 = "select username, password,enabled from users where username=:username";
			MyUser queryResult = jdbc.queryForObject(sql1, new MapSqlParameterSource("username", username), 
					(rs, rownum) -> MyUser.builder().username(rs.getString("username"))
													.password(rs.getString("password"))
													.enabled(rs.getBoolean("enabled"))
													.build()
												);
			
			String sql2 = "select role  from user_roles where username=:username";
			List<GrantedAuthority> list = jdbc.query(sql2, new MapSqlParameterSource("username", username), 
					(rs, rownum) -> new SimpleGrantedAuthority(rs.getString("role"))
					);
			queryResult.setAuthorities(list);	
			
			return queryResult;
			
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new UsernameNotFoundException("Could not find user " + e.getMessage());
		}
	}
	
	
	
	
	

}
