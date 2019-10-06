package com.developer.storesws.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/doc")
public class ServiceDocumentController {
	

	@GetMapping(produces  = "application/hal+json;charset=UTF-8")
	public Resource<String> getServiceDocument(){
		 Resource<String> result = new Resource<>("test",
				 BasicLinkBuilder.linkToCurrentMapping().withSelfRel(),
				 BasicLinkBuilder.linkToCurrentMapping().slash("/store").withRel("stores")
				);
		 
		 return result;
	}
	
	@PostMapping("/validate")
	public Resource<String> validate(@RequestBody @Valid ValidateMe validateMe){
		return new  Resource<String>("ok");
	}
	
	public static class ValidateMe{
		@Min(21)
		int age;
		@Length(max = 6, min = 4)
		String name;
		@Min(5)@Max(1000)
		int arbitraryValue;
		ValidateMe child;
		@Email
		String email;
		@Future
		Date nextBirthday;
		@Past
		Date birthday;
		public ValidateMe(int age, String name, int arbitraryValue, ValidateMe child, String email, Date nextBirthday,
				Date birthday) {
			this.age = age;
			this.name = name;
			this.arbitraryValue = arbitraryValue;
			this.child = child;
			this.email = email;
			this.nextBirthday = nextBirthday;
			this.birthday = birthday;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getArbitraryValue() {
			return arbitraryValue;
		}
		public void setArbitraryValue(int arbitraryValue) {
			this.arbitraryValue = arbitraryValue;
		}
		public ValidateMe getChild() {
			return child;
		}
		public void setChild(ValidateMe child) {
			this.child = child;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Date getNextBirthday() {
			return nextBirthday;
		}
		public void setNextBirthday(Date nextBirthday) {
			this.nextBirthday = nextBirthday;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		
	}

}
