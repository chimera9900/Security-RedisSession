package com.developer.storesws.ui.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.developer.storesws.model.Store;
import com.developer.storesws.service.StoreService;

@Controller
@RequestMapping(value = "/api/store")
@PreAuthorize("hasRole('USER')")
public class StoreUiController {
	
	@Autowired
	StoreService storeService;
	
	@ModelAttribute(value = "store")
	public Store store(@PathVariable Optional<String> uuid) {
		return uuid.map(x -> storeService.find(x))
				.orElseGet(Store::new);
	}
	
	@GetMapping(value = "/{uuid}")
	public String getStore() {
		return "store/details";
	}
	
	@GetMapping(value = "/{uuid}/delete")
	public String deleteStore(@PathVariable String uuid) {
		storeService.remove(uuid);
		return "redirect:/api/stores";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/{uuid}",params = "edit=true")
	public String editStore( Model model, @RequestParam boolean edit) {
		model.addAttribute("edit", edit);
		
		return "store/edit";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/{uuid}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String updateStore(@PathVariable String uuid, @Valid Store store, BindingResult result) {
		if(result.hasErrors()) {
			return "store/edit";
		}
		storeService.updateStore(uuid, store);
		return "redirect:/api/stores";
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public String newStore(Model model) {
		model.addAttribute("edit", false);
		return "store/edit";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String saveNewStore(@Valid Store store, BindingResult result) {
		if(result.hasErrors()) {
			return "store/edit";
		}
		storeService.addStore(store);
		return "redirect:/api/stores";
	}

}
