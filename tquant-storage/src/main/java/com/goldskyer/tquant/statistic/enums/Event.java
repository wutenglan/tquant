package com.goldskyer.tquant.statistic.enums;

public enum Event {
	实际涨停("昨日买入涨停，今日仍涨停，或涨幅达8%"),实际有效("昨天涨停，今天上午涨幅>=1%"),实际亏损("昨天涨停，今天上午涨幅<1%"),当日涨停("今天涨停的个股"),当日涨停可买("今天涨停，且存在缺口"),一字涨停("全天都是涨停");
	private String desc;
	
	Event(String desc2)
	{
		this.desc=desc2;
	}
}
