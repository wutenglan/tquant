package com.goldskyer.tquant.storage.monitor.vo;

public class TickIndex
{
	public static final int EMPTY_DATA_TICK_ID = -1;
	public static final int MIN_TICK_ID = 0;//对应9:25:00
	public static final int MAX_TICK_ID = 14760; //13:01:00
	private int tickId; //当前tick隶属节点
	private int dataTickId; //

	public int getTickId()
	{
		return tickId;
	}

	public void setTickId(int tickId)
	{
		this.tickId = tickId;
	}

	public int getDataTickId()
	{
		return dataTickId;
	}

	public void setDataTickId(int dataTickId)
	{
		this.dataTickId = dataTickId;
	}

	public static int getMinTickId()
	{
		return MIN_TICK_ID;
	}

	public static int getMaxTickId()
	{
		return MAX_TICK_ID;
	}

}
