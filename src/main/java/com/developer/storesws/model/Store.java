package com.developer.storesws.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;


public class Store {
	
	@Length(max = 255)
	@NotEmpty
	@URL
	private String url;
	private String uuid;
	private int version;
	@Length(max = 255, min = 5)
	private String description;
	@JsonFormat(shape = Shape.STRING, pattern = "dd. MM YYYY")
	private LocalDateTime createOn;
	private LocalDateTime updateOn;
	
	
	public Store() {
	}
	
	public Store(String url) {
		this.url = url;
	}
	

	public Store(String url, String description) {
		this.url = url;
		this.description = description;
	}

	@JsonCreator
	public Store(@JsonProperty("url") String url,
			@JsonProperty("uuid") String uuid,
			@JsonProperty("version") int version,
			@JsonProperty("description") String description,
			@JsonProperty("createOn") LocalDateTime createOn,
			@JsonProperty("updateOn") LocalDateTime updateOn
	) {
		this.url = url;
		this.uuid = uuid;
		this.version = version;
		this.description = description;
		this.createOn = createOn;
		this.updateOn = updateOn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public  Store withUuid(String uuid) {
		return new Store(url, uuid, version, description, createOn, updateOn);
		
	}
	public  Store withUrl(String url) {
		return new Store(url, uuid, version, description, createOn, updateOn);
		
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}

	public LocalDateTime getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(LocalDateTime updateOn) {
		this.updateOn = updateOn;
	}
	

}
