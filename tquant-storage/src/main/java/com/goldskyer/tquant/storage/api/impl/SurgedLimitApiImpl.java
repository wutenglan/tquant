package com.goldskyer.tquant.storage.api.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.api.SurgedLimitApi;
@Service("surgedLimitApi")
public class SurgedLimitApiImpl implements SurgedLimitApi{
	
	
    public String sayHello(@QueryParam("name") String name) {
        return "surge " + name;
    }
	
	
    public String sayHello2(String callback) {
    	String jsonP=callback+"([[Date.UTC(2013,5,2),0.7695],[Date.UTC(2013,5,3),0.7648],[Date.UTC(2013,5,4),0.7645],[Date.UTC(2013,5,5),0.7638],[Date.UTC(2013,5,6),0.7549],[Date.UTC(2013,5,7),0.7562],[Date.UTC(2013,5,9),0.7574]]);";
        return jsonP ;
    }
}
