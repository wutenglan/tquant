package com.goldskyer.tquant.storage.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("demo")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON + ";"+MediaType.CHARSET_PARAMETER+"=UTF-8",MediaType.TEXT_XML+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
public interface DemoService {

	@GET
    @Path("sayHello")
	public String sayHello();
	
	@GET
    @Path("sayHello1")
	public String sayHello1(@QueryParam("name")String name);
	

	
}
