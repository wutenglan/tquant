package com.goldskyer.tquant.base;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioTest
{

	public static void main(String[] args) throws Exception
	{
		Clip clip = AudioSystem.getClip();
		// getAudioInputStream() also accepts a File or InputStream
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/data/sound.wav"));
		clip.open(ais);
		clip.loop(0);
		Thread.sleep(10000);
	}
}
