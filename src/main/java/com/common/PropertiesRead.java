package com.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.springframework.stereotype.Component;
import com.mysql.fabric.xmlrpc.base.Data;

@Component
public class PropertiesRead {
	private Properties prop = new Properties();
	private InputStream in = null;
	private String fileName = null;
	
	/* get set function start*/
	public Properties getProp() {
		return prop;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/* get set function end*/

	/**
	 * 加载文件内容
	 * @return
	 */
	protected Properties load(){
		try {
			if(fileName==null){
				System.out.println("place setting filename");
				return null;
			}else{
				if(in==null){
					in = new BufferedInputStream (new FileInputStream(fileName)); 
				}
				prop.load(in);
				return prop;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("load() error");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 关闭流资源
	 */
	protected void Close(){
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("close() error");
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 读取所用配置信息
	 * @return
	 */
	public Map<String, Object> getAllItem(){
		Map<String, Object> map = new HashMap<String, Object>();
		prop = load();
		Iterator<String> it = prop.stringPropertyNames().iterator();
		while(it.hasNext()){
			String key = it.next();
			map.put(key, prop.getProperty(key));
		}
		Close();
		if(map.size()==0){
			return null;
		}
		return map;
	}
	
	/**
	 * 根据关键值读取值
	 * @param key
	 */
	public Object getItemByKey(String key){
		if(key==null||key.equals("")){
			System.out.println("getItemByKey() key is null");
			return null;
		}
		Map<String, Object> map = getAllItem();
		Object object = map.get(key);
		if(object==null){
			System.out.println("getItemByKey() find the key not item");
		}
		return object;
	}

	/**
	 * 添加配置信息
	 * @param map
	 * @param IsUpdateFromEnd
	 */
	public boolean addItem(Map<String, String> map,boolean IsUpdateFromEnd){
		if(fileName==null|fileName.equals("")){
			System.out.println("not filename");
			return false;
		}
		try {
			FileOutputStream os = new FileOutputStream(fileName,IsUpdateFromEnd);//true是在文件后追加
			for(String key : map.keySet()){
				prop.setProperty(key, map.get(key));
			}
			prop.store(os, "update from "+new Data().toString());
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("addItem() savefile error");
			e.printStackTrace();
			return false;
		}
	}
	
}
