package com.goldskyer.tquant.storage.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("surgeLimit")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON + ";"+MediaType.CHARSET_PARAMETER+"=UTF-8",MediaType.TEXT_XML+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
public interface SurgedLimitApi {

	@GET
    @Path("sayHello")
    public String sayHello(@QueryParam("name") String name) ;  
    
	
	@GET
    @Path("sayHello1")
    public String sayHello2(@QueryParam("callback") String callback);
}
