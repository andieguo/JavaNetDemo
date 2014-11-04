package com.andieguo.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadURL {
	
	//通过url.openStream()获取InputStream，并通过InputStream流访问Internet。
	private static void readHTML0() {
		try{
			URL url = new URL("http://www.baidu.com");
			InputStream in = url.openStream();
			byte[] b = new byte[1024];
			int len = 0;
			while((len = in.read(b)) != -1){
				System.out.println(new String(b,0,len,"UTF-8"));
			}
			if(in != null) in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//通过url.openConnection()获取URLConnection连接，并通过conn.getInputStream()获取InputStream访问Internet。
	private static void readHTML1() {
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			byte[] b = new byte[1024];
			int len = 0;
			while((len = in.read(b))!=-1){
				System.out.println(new String(b,0,len,"UTF-8"));
			}
			if(in != null) in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//通过url.openConnection()获取URLConnection连接，并通过conn.getInputStream()获取InputStream，并通过BufferedReader对InputStream进行封装访问Internet。
	private static void readHTML2() {
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			String str = "";
			while( (str = reader.readLine()) != null){
				System.out.println(str);
			}
			if(in != null) in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
