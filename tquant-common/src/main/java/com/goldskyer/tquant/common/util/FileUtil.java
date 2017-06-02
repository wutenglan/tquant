package com.goldskyer.tquant.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.List;

public class FileUtil {

	/**
	 * 新建目录
	 * @param path 完整路径(可以是文件名，也可以是文件路径)
	 * @return 是否成功新建目录(目录已存在，不创建目录，返回false；目录不存在，创建目录，返回true)
	 * @author hzjintianfan 2011-12-23
	 */
	public static boolean mkdir(String path) {
		java.io.File file = new java.io.File(path);
		if(!file.exists()) {
			return file.mkdirs();
		}
		return false;
	}
	
	/**
	 * 递归删除目录及目录下所有文件、子目录
	 * @param folderPath 目录路径
	 * @author hzjintianfan 2011-12-23
	 */
	public static void rm(String folderPath) {
		try {
			rmSub(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除目录下所有文件及子目录(不包含本目录）
	 * @param path 目录路径
	 * @return 是否成功删除
	 * @author hzjintianfan 2011-12-23
	 */
	public static boolean rmSub(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				rmSub(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				rm(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 获取文件扩展名。
	 * 如: "example.tar.gz" 返回 "gz"
	 * @param file 文件
	 * @return
	 * @author xuhan 2012-9-19
	 */
	public static String getExtension(File file) {
		if (file == null) {
			return "";
		}
		int dotIndex = file.getName().lastIndexOf('.');
		return dotIndex >= 0 ? file.getName().substring(dotIndex+1) : "";
	}

	/**
	 * 获取文件所在的目录
	 * @param fullPath
	 * @return
	 */
	public static String getDirectory(String fullPath){
		int splitIndex = fullPath.lastIndexOf(File.separator);
		if(splitIndex==-1)
			return "";
		return fullPath.substring(0, splitIndex);
	}
	
	
	public static void writeFileContent(String filename,String fileContent){
		writeFileContent( filename, fileContent,false);
	}
	/**
	 * 把文本写入文件中()
	 * @param filename
	 * @param fileContent
	 * @return
	 */
	public static void writeFileContent(String filename,String fileContent,boolean append ){
		try {  
            File f = new File(filename);  
            if (!f.getParentFile().exists()) {  
                f.getParentFile().mkdirs();  
            }  
            FileOutputStream out = new FileOutputStream(filename,append);  
            byte[] bytes = fileContent.getBytes("UTF-8");  
            out.write(bytes);  
            out.flush();  
            out.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
	}
	
	/**
	 * 遍历一个目录下所有文件及文件夹
	 * @param file
	 * @param fileList
	 */
	public static void ls(File file,List<File> fileList){
		fileList.add(file);
		File[] files = file.listFiles();
		if (files != null) { 
			for(File f :files){
				ls(f, fileList);
			}
		}
	}
	
	/**
	 * 返回文件最后一行
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readLastLine(File file, String charset)   {  
		  if (!file.exists() || file.isDirectory() || !file.canRead()) {  
		    return null;  
		  }  
		  RandomAccessFile raf = null;  
		  try {  
		    raf = new RandomAccessFile(file, "r");  
		    long len = raf.length();  
		    if (len == 0L) {  
		      return "";  
		    } else {  
		      long pos = len - 1;  
		      while (pos > 0) {  
		        pos--;  
		        raf.seek(pos);  
		        if (raf.readByte() == '\n') {  
		          break;  
		        }  
		      }  
		      if (pos == 0) {  
		        raf.seek(0);  
		      }  
		      byte[] bytes = new byte[(int) (len - pos)];  
		      raf.read(bytes);  
		      if (charset == null) {  
		        return new String(bytes);  
		      } else {  
		        return new String(bytes, charset);  
		      }  
		    }  
		  } catch (Exception e) {  
		  } finally {  
		    if (raf != null) {  
		      try {  
		        raf.close();  
		      } catch (Exception e2) {  
		      }  
		    }  
		  }  
		  return null;  
	}
	
	
	public static String readFirstLine(File file,String charset) throws IOException  {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			br.close();
		}
		return line;
	}
	
	/**
	 * 统计文件总行数
	 * @param filePath
	 * @return
	 */
	public static Integer countFileLines(String filePath){
		int i = 0;
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			while(br.readLine() != null){
			i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return i;
	}
	
	public static StringBuffer readFileContent(String filename){
		return readFileContent(filename, "UTF-8");
	}
	public static StringBuffer readFileContent(String filename,String charset){
		StringBuffer buffer = new StringBuffer();
		String line = null;
        BufferedReader reader=null;
        try {  
        	InputStream is = new FileInputStream(filename);  
        	reader = new BufferedReader(new InputStreamReader(is,  
                charset));  
            line = reader.readLine();  
        } catch (IOException e) {
            e.printStackTrace();  
        }
        while (line != null) {
            buffer.append(line);
            buffer.append("\r\n");
            try {  
                line = reader.readLine();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }
		return buffer;
	}
	
	
	public static void main(String[] args) {
		System.out.println(mkdir("/data/www/storage/xcxx/201608/1253ca.crt"));
	}
}
