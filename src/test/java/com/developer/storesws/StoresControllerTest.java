//package com.developer.storesws;
//
//import static org.hamcrest.CoreMatchers.any;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.awt.List;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.UUID;
//
//import org.assertj.core.util.Arrays;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.Resources;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.developer.storesws.model.Store;
//import com.developer.storesws.service.StoreService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@Transactional
//public class StoresControllerTest {
//	@Autowired
//	MockMvc mvc;
//	@Autowired
//	ObjectMapper mapper;
//	@MockBean
//	StoreService storeService;
//	
//	@Test
//	public void addStore() throws JsonProcessingException, Exception {
//		String input = "http://google.com";
//		addStore(input);
//		
//		verify(storeService).addStore(Mockito.any(Store.class));
//	}
//	
//	@Test
//	public void findAllStore() throws JsonProcessingException, Exception {
//		String input1= "http://google.com";
//		String input2 = "http://yahoo.com";
//		
////		addStore(input1);
////		addStore(input2);
////		
//		Collection<Store> list = new ArrayList<Store>();
//		list.add(new Store(input1, UUID.randomUUID(),1));
//		list.add(new Store(input2, UUID.randomUUID(),1));
//		
//		when(storeService.findAll()).thenReturn(list);
//		
//		String result = mvc.perform(MockMvcRequestBuilders.get("/stores"))
//		.andReturn().getResponse().getContentAsString();
//		System.out.println(result);
//		
//		Resources<Store> output = mapper.readValue(result, new TypeReference<Resources<Store>>() {
//		});
//		
//		System.out.println(output.getContent());
//		
////		assertTrue(output.getContent().size() >= 2);
//		
//		
//	}
//	
//	private void addStore(String input) throws Exception, JsonProcessingException {
//		mvc.perform(
//				MockMvcRequestBuilders.post("/stores")
//				.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(mapper.writeValueAsString(new Store(input)))
//				).andExpect(status().isCreated());
//	}
//	
//}
