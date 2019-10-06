package com.developer.storesws.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.storesws.config.StoreResourceAssembler;
import com.developer.storesws.model.Store;
import com.developer.storesws.service.StoreService;

@RestController
@RequestMapping(value = "store", produces = "application/hal+json;charset=UTF-8")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	@Autowired
	StoreResourceAssembler assembler;
	
	@GetMapping("/{id}")
	public Resource<Store> getStore(@PathVariable UUID id) {
		
		Store store = storeService.find(id);
		return assembler.toResource(store);
		
	}
	
	@PostMapping("/{id}")
	public Resource<Store> updateStore(@PathVariable UUID id, @RequestBody Store store){
		Store updateStore = storeService.updateStore(id, store);
		
		return assembler.toResource(updateStore);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStore(@PathVariable UUID id){
	
			try {
				storeService.remove(id);
				return ResponseEntity.status(HttpStatus.GONE).build();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No this element in database");
			}
	
	}
	

}
