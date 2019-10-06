package com.developer.storesws.config;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.developer.storesws.controller.StoreController;
import com.developer.storesws.model.Store;

@Component
public class StoreResourceAssembler implements ResourceAssembler<Store, Resource<Store>> {
	public Resource<Store> toResource(Store entity) {
		return new Resource<Store>(entity,
				linkTo(methodOn(StoreController.class).getStore(entity.getUuid())).withSelfRel()
				);
	};
}
