package com.developer.storesws.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.developer.storesws.config.TestBean;
import com.developer.storesws.model.Store;

@Service
@Transactional
public class StoreService {
	@Autowired
	NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	TestBean testBean;
	
	public String addStore(Store store)  {
		String uuid = UUID.randomUUID().toString();
		String sql = "insert into store (url, uuid, version, description, createOn) values(:url, :uuid, 1, :description, :createOn)";
		jdbc.update(sql, new MapSqlParameterSource("url", store.getUrl())
				.addValue("uuid", uuid)
				.addValue("description", store.getDescription())
				.addValue("createOn", LocalDateTime.now())
				);
//		System.out.println(TransactionInterceptor.currentTransactionStatus());
		System.out.println("http://localhost:8080/store/" + uuid);
		return uuid;
		
	}
	
	public Store find(String id) {
		String sql = "select * from store where uuid = :uuid";
		try {
			return jdbc.queryForObject(sql, new MapSqlParameterSource("uuid", id), new StoreRowMapper());
		}catch(NoSuchElementException e) {
			throw new NoSuchElementException();
		}
	}
	
	public Collection<Store> findAll() {
		System.out.println("service : " + testBean.getData());
		String sql = "select * from store";
		List<Store> list = jdbc.query(sql, new StoreRowMapper());
		return list;
}
	
	public Store updateStore(String id, Store store) {
		find(id);
		String sql = "update store set url=:url, version = version + 1, description=:description, updateOn=:updateOn"
				+ " where uuid=:uuid";
		int i = jdbc.update(sql, new MapSqlParameterSource("url", store.getUrl())
				.addValue("uuid", id)
				.addValue("description", store.getDescription())
				.addValue("updateOn", LocalDateTime.now())
				);
		if(i!=1) throw new OptimisticLockingFailureException("Stale update detected for " + store.getUuid());
		return find(id);
	}

	public void remove(String id) {
		
		String sql = "delete from store where uuid = :uuid";
		
		int i = jdbc.update(sql, new MapSqlParameterSource("uuid", id));
		if(i != 1) {
			throw new NotModifiedDataAccessException("store already gone");
		};
	}
}

class StoreRowMapper implements RowMapper<Store>{
	@Override
	public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
		Store store = new Store();
		store.setUrl(rs.getString("url"));
		store.setUuid(rs.getString("uuid"));
		store.setVersion(rs.getInt("version"));
		store.setCreateOn(rs.getObject("createOn", LocalDateTime.class));
		store.setDescription(rs.getString("description"));
		store.setUpdateOn(rs.getObject("updateOn", LocalDateTime.class));
		return store;
	}
}

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
class NotModifiedDataAccessException extends DataAccessException{
	
	private static final long serialVersionUID = 1L;

	public NotModifiedDataAccessException(String msg) {
		super(msg);
	}
}