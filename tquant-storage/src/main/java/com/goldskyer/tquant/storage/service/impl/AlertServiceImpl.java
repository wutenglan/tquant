package com.goldskyer.tquant.storage.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.storage.constant.PathConstant;
import com.goldskyer.tquant.storage.dto.AlertDTO;
import com.goldskyer.tquant.storage.service.AlertService;
import com.goldskyer.tquant.storage.utils.MarketCnUtil;

@Service
public class AlertServiceImpl implements AlertService
{
	private static final Log log = LogFactory.getLog(AlertServiceImpl.class);
	private static final Map<String, AlertDTO> shortcutMap = new HashMap<>();
	/**
	 * 白名单股票触发音频报警
	 */
	public void fireAlert(AlertDTO alertDTO)
	{
		String soundList = "000935,600275,002112,000567,002722,600868";
		if (soundList.contains(MarketCnUtil.getMarketCnCodeBySysCode(alertDTO.getSysCode())))
		{
			alertSound(alertDTO);
			alertLogImportant(alertDTO);
		}
		alertLog(alertDTO);
		alertLogShortCut(alertDTO);
	}

	private void alertSound(AlertDTO alertDTO)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/data/sound.wav"));
			clip.open(ais);
			clip.loop(0);
		}
		catch (Exception e)
		{
			log.fatal(e.getMessage(), e);
		}
	}

	private void alertLog(AlertDTO alertDTO)
	{

		alertLogToFile(alertDTO, "");
	}

	private void alertLogToFile(AlertDTO alertDTO, String prefix)
	{

		List<String> lines = new ArrayList<>();
		String line = alertDTO.getTime() + "," + alertDTO.getAlertType() + "," + alertDTO.getSysCode() + ","
				+ alertDTO.getScore();
		lines.add(line);
		try
		{
			IOUtils.writeLines(lines, "\r\n", new FileOutputStream(
					PathConstant.HOME + "/alert/" + DateUtil.date2String(new java.util.Date()) + "_" + prefix + ".txt",
					true));
		}
		catch (Exception e)
		{
			log.fatal(e.getMessage(), e);
		}
	}

	public void alertLogImportant(AlertDTO alertDTO)
	{
		if (shortcutMap.containsKey(alertDTO.getSysCode())
				&& ((new Date().getTime() - shortcutMap.get(alertDTO.getSysCode()).getPutTime().getTime()) < 1000 * 60
						* 5))//五分钟短路时间，防止连续触发报警
		{
			return;
		}
		alertLogToFile(alertDTO, "important");
		alertDTO.setPutTime(new Date());
		shortcutMap.put(alertDTO.getSysCode(), alertDTO);
	}

	private void alertLogShortCut(AlertDTO alertDTO)
	{
		if (shortcutMap.containsKey(alertDTO.getSysCode())
				&& ((new Date().getTime() - shortcutMap.get(alertDTO.getSysCode()).getPutTime().getTime()) < 1000 * 60
						* 5))//五分钟短路时间，防止连续触发报警
		{
			return;
		}
		alertLogToFile(alertDTO, "shortcuts");
		alertDTO.setPutTime(new Date());
		shortcutMap.put(alertDTO.getSysCode(), alertDTO);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		List<String> lines = new ArrayList<>();
		String line = "xxx";
		lines.add(line);
		IOUtils.writeLines(lines, "\r\n", new FileOutputStream("/data/ok.txt", true));
	}
}
