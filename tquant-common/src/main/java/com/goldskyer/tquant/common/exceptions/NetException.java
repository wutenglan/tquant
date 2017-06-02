package com.goldskyer.tquant.common.exceptions;

public class NetException extends RuntimeException{
	private static final long serialVersionUID = 1L;	
	
	public NetException(){
		super();
	}
	
	public NetException(String msg){
		super(msg);
	}
	
	public NetException(Exception exception)
	{
		super(exception);
	}

	public NetException(String msg, Exception e)
	{
		super(msg, e);
	}

}
