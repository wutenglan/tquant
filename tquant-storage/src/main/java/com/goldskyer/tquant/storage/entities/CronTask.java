package com.goldskyer.tquant.storage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.goldskyer.tquant.storage.enums.Switch;

/**
 * 
 * @author jintianfan
 *
 */
@Table(name = "cron_task")
@Entity
public class CronTask
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String taskName;

	private String group;//任务分组
	private String express;
	@Enumerated(EnumType.STRING)
	private Switch status;
	private String taskBean;

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getExpress()
	{
		return express;
	}

	public void setExpress(String express)
	{
		this.express = express;
	}

	public Switch getStatus()
	{
		return status;
	}

	public void setStatus(Switch status)
	{
		this.status = status;
	}

	public String getTaskBean()
	{
		return taskBean;
	}

	public void setTaskBean(String taskBean)
	{
		this.taskBean = taskBean;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

}
