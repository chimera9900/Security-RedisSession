package com.developer.storesws.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.storesws.model.Store;
import com.developer.storesws.service.StoreService;

@RestController
@RequestMapping("/stores")
public class StoresController {
	
	@Autowired
	StoreService storeService;
	
	@PostMapping
	public ResponseEntity<Void> addStore(@RequestBody @Valid Store store) throws MalformedURLException, URISyntaxException {
		UUID uuid = storeService.addStore(store);
		return ResponseEntity.created(
				BasicLinkBuilder.linkToCurrentMapping().slash("store").slash(uuid).toUri()
				).build();
	}
	
	@GetMapping
	public Resources<Store> findAll() {
		
		return new Resources<>( storeService.findAll(),
				BasicLinkBuilder.linkToCurrentMapping().slash("stores").withSelfRel()
				);		
	
	}
	

}
