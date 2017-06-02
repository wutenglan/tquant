package com.goldskyer.tquant.common.exceptions;

@SuppressWarnings("serial")
public class SysException extends RuntimeException
{

	public SysException()
	{
		super();
	}

	public SysException(Exception exception)
	{
		super(exception);
	}

	public SysException(String msg)
	{
		super(msg);
	}

	public SysException(String msg, Exception e)
	{
		super(msg, e);
	}
}