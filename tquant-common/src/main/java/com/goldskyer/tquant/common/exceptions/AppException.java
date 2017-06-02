package com.goldskyer.tquant.common.exceptions;

/**
 * App异常类，基类
 * @author jintianfan
 *
 */
public class AppException extends RuntimeException
{

	public AppException()
	{
		super();
	}

	public AppException(Exception exception)
	{
		super(exception);
	}

	public AppException(String msg)
	{
		super(msg);
	}

	public AppException(String msg, Exception e)
	{
		super(msg, e);
	}
}
