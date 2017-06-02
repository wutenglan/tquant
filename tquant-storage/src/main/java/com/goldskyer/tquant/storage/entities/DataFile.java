package com.goldskyer.tquant.storage.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.goldskyer.tquant.storage.enums.DataFileType;
import com.goldskyer.tquant.storage.enums.DataUnitCompAlgor;
import com.goldskyer.tquant.storage.enums.FileCompAlgor;

/**
 * 数据文件
 * 描述：打算存储每日沪深个股分时数据，沪深指数分时数据
 * @author jintianfan
 *
 */
@Table(name = "data_file")
@Entity
public class DataFile
{
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;

	@Enumerated(EnumType.STRING)
	private DataFileType dataFileType;

	private String scsUrl;
	
	private String dateString;
	
	private long fileSize;
	
	private DataUnitCompAlgor dataUnitCompAlgor;

	private FileCompAlgor fileCompAlgor;

	private Date createDate;

	public DataFileType getDataFileType()
	{
		return dataFileType;
	}

	public void setDataFileType(DataFileType dataFileType)
	{
		this.dataFileType = dataFileType;
	}

	public String getScsUrl()
	{
		return scsUrl;
	}

	public void setScsUrl(String scsUrl)
	{
		this.scsUrl = scsUrl;
	}

	public String getDateString()
	{
		return dateString;
	}

	public void setDateString(String dateString)
	{
		this.dateString = dateString;
	}

	public long getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(long fileSize)
	{
		this.fileSize = fileSize;
	}

	public DataUnitCompAlgor getDataUnitCompAlgor()
	{
		return dataUnitCompAlgor;
	}

	public void setDataUnitCompAlgor(DataUnitCompAlgor dataUnitCompAlgor)
	{
		this.dataUnitCompAlgor = dataUnitCompAlgor;
	}

	public FileCompAlgor getFileCompAlgor()
	{
		return fileCompAlgor;
	}

	public void setFileCompAlgor(FileCompAlgor fileCompAlgor)
	{
		this.fileCompAlgor = fileCompAlgor;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
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
