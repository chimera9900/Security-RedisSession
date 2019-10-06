//package com.developer.storesws;
//
//import static org.hamcrest.CoreMatchers.any;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.UUID;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.hateoas.Resource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.developer.storesws.model.Store;
//import com.developer.storesws.service.StoreService;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@Transactional
//public class StoreControllerTest {
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
//		
//		String location = mvc.perform(
//				MockMvcRequestBuilders.post("/stores")
//				.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(mapper.writeValueAsString(new Store(input)))
//				)
//				.andReturn().getResponse().getHeader("Location")
//				;
//		System.out.println("%%%%: " + location);
//		
//		when(storeService.find(Mockito.any(UUID.class))).thenReturn(new Store(input, UUID.randomUUID(),1));
//		
//		String url = "http://localhost:8080/store/14ad3953-b8fe-4984-9b07-22c494411fed";
//
//		String result = mvc.perform(MockMvcRequestBuilders.get(url))
//		.andReturn().getResponse().getContentAsString();
//		
//		System.out.println(result);
//		
//		Resource<Store> output = mapper.readValue(result, new TypeReference<Resource<Store>>() {
//		});
//
//		assertEquals(input, output.getContent().getUrl());
//		
//	}
//	
//	@Test
//	public void deleteStore() throws JsonProcessingException, Exception {
//		
//		String input = "http://google.com";
//		String location = mvc.perform(
//				MockMvcRequestBuilders.post("/stores")
//				.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(mapper.writeValueAsString(new Store(input)))
//				)
//				.andReturn().getResponse().getHeader("Location")
//				;
//		
//		UUID id = UUID.randomUUID();		
//		when(storeService.addStore(Mockito.any(Store.class))).thenReturn(id);
//		String url = "http://localhost:8080/store/" + id;
//		mvc.perform(delete(url))
//		.andExpect(status().isGone());
//	
////		mvc.perform(delete(url))
////		.andExpect(status().isNotFound());
//		
//	}
//	
//
//
//	
////	@Test
////	public void updateStore() throws JsonProcessingException, Exception {
////		String url = "yahoo.com";
////		Store updateStore = new Store("yahoo1.com");
////		
////		UUID id = UUID.randomUUID();
////		
////		when(storeService.addStore(Mockito.any(Store.class))).thenReturn(id);
////		String postUrl = "http://localhost:8080/store/" + id;
////		
////		when(storeService.updateStore(id, updateStore)).thenReturn(updateStore.withUuid(id));
////		
////		String result = mvc.perform(MockMvcRequestBuilders.post(postUrl)
////				.contentType("application/hal+json;charset=UTF-8")
////				.content(mapper.writeValueAsString(updateStore))
////				).andReturn().getResponse().getContentAsString();
////		
////		System.out.println("xxxx: " + result);
////		
////		
////	}
//	private String location(String input) throws Exception, JsonProcessingException {
//		String location = mvc.perform(
//				MockMvcRequestBuilders.post("/stores")
//				.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(mapper.writeValueAsString(new Store(input)))
//				)
//				.andReturn().getResponse().getHeader("Location")
//				;
//		
//		return location;
//	}
//}
