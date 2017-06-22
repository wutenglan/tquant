package com.goldskyer.tquant.storage.api.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CrosFilter implements ContainerResponseFilter {

	private static final Log LOGGER = LogFactory.getLog(CrosFilter.class);

	@Override
	public void filter(ContainerRequestContext a1, ContainerResponseContext containerResponseContext)
			throws IOException {
		// TODO Auto-generated method stub
		containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		containerResponseContext.getHeaders().add("Access-Control-Allow-Headers",
				"Content-Type,x-requested-with,Authorization,Access-Control-Allow-Origin");
		containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		containerResponseContext.getHeaders().add("Access-Control-Max-Age", "360");
	}

}