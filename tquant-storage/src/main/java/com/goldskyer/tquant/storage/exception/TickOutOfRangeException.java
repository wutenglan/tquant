package com.goldskyer.tquant.storage.exception;

import com.goldskyer.tquant.common.exceptions.AppException;

public class TickOutOfRangeException extends AppException
{
	public TickOutOfRangeException()
	{
		super();
	}

	public TickOutOfRangeException(Exception exception)
	{
		super(exception);
	}

	public TickOutOfRangeException(String msg)
	{
		super(msg);
	}

	public TickOutOfRangeException(String msg, Exception e)
	{
		super(msg, e);
	}
}
