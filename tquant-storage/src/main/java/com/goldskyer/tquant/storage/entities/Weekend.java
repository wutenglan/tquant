package com.goldskyer.tquant.storage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.goldskyer.tquant.storage.enums.Exchange;

/**
 * 非星期日法定假日（该表用于找到缺失的日线）
 * 
 * @author jintianfan
 *
 */
@Entity
@Table(name = "WEEKEND")
public class Weekend {
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String dateString;
	@Enumerated(EnumType.STRING)
	private Exchange exchange;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Exchange getExchange()
	{
		return exchange;
	}

	public void setExchange(Exchange exchange)
	{
		this.exchange = exchange;
	}


}
