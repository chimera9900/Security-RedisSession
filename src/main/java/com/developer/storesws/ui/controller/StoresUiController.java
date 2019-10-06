package com.developer.storesws.ui.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.developer.storesws.config.TestBean;
import com.developer.storesws.model.Store;
import com.developer.storesws.service.StoreService;

@Controller
@RequestMapping("/api/stores")
public class StoresUiController {
	@Autowired
	StoreService storeService;
	@Autowired
	TestBean testBean;
	
	@RequestMapping
	@PreAuthorize("isAuthenticated()")
	public String allStores(Model model, @RequestParam Optional<String> data) {
		
		data.ifPresent(x -> testBean.setData(x));		
		System.out.println("list ui: " + testBean.getData());
		model.addAttribute("testBean", testBean.getData());
		
		Collection<Store> stores = storeService.findAll();
		model.addAttribute("stores", stores);
		return "stores/list";
	}
	
}


