package com.andieguo.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
	/**
	 * InetAddress类没有提供返回构造函数，所以不能用new()方法来创建它的对象，而只可以调用静态方法getLocalHost()、getByName()、getByAddress()等来生成InetAddress类的实质。
	 * @param args
	 */
	public static void main(String[] args) {
		 try {
			InetAddress address= InetAddress.getLocalHost();
			System.out.println("本机IP地址字符串:"+address.getHostAddress());
			System.out.println("本机用户名:"+address.getHostName());
			System.out.println("本机主机名:"+address.toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
