## Java获取URL对应的资源 ##

　　在JAVA中，Java.net包里面的类是进行网络编程的，其中java.net.URL类和java.net.URLConection类使编程者方便地利用URL在Internet上进行网络通信。

### 1、认识URL（Java API doc） ###

> 类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询。
>  
> 简单的可以把URL理解为包含：协议、主机名、端口、路径、查询字符串和参数等对象。每一段可以独立设置。
>  
> 应用程序也可以指定一个“相对 URL”，它只包含到达相对于另一个 URL 的资源的足够信息。HTML 页面中经常使用相对 URL。
>  
> 相对 URL 不需要指定 URL 的所有组成部分。如果缺少协议、主机名称或端口号，这些值将从完整指定的 URL 中继承。
>  
> 由于 URL 不懂 URL 转义，所以它不会识别同一 URL 的对等编码和解码形式。
>  
> 注意，URI 类在某些特定情况下对其组成字段执行转义。建议使用 URI 管理 URL 的编码和解码，并使用 toURI() 和 URI.toURL() 实现这两个类之间的转换。
>  
> 也可以使用 URLEncoder 和 URLDecoder 类，但是只适用于 HTML 形式的编码，它与 RFC2396 中定义的编码机制不同。


### 2、创建URL对象 ###  
　　URL类有多种形式的构造函数：

> **URL(String url)**  
> //url代表一个绝对地址，URL对象直接指向这个资源，如：  
> URL urll=new URL("http://www.baidu.com");  
> 
> **URL(URL baseURL,String relativeURL)**  
> //其中，baseURL代表绝对地址，relativeURL代表相对地址。如：  
> URL urll=new URL("http://www.baidu.com");  
> URL lib=new URL(urll , "library/library.asp");  
> 
> **URL ( String protocol , String host , String file)**  
> //其中，protocol代表通信协议，host代表主机名，file代表文件名。如：  
> URL url = new URL("http" ,"http://www.baidu.com","/test/test.asp");  
> 
> **URL(String protocol,String host,int port,String file)**  
> URL lib = new URL("http","http://www.baidu.com",80 ,"/test/test.asp");  


### 3、InetAddress类 ###
　java.net包可以用32位int形式来操作32位的IP地址(即Internet主机地址)。类InetAddress实际上是可以把Internet地址换算成代表该地址的对象。Java就是靠这个类来显示Internet地址已经相关信息的。  

- **InetAddress常用方法**  

> **getAddress()**： 返回IP地址的字节形式。  
> **getAllByName()**： 返回指定主机名的IP地址。  
> **getbyAddress()**： 返回指定字节数组的IP地址形式。  
> **getByName()**： 返回指定主机名的IP地址对象。  
> **getHostAddress()**： 返回主机地址的字符串形式。  
> **getLocalHost()**： 返回当前主机名。  
> **hastCode()**： 返回InetAddress对象的哈希码。  
> **toString()**： 返回地址转换成的字符串。  

- **程序源码**  

　InetAddress类没有提供返回构造函数，所以不能用new()方法来创建它的对象，而只可以调用静态方法getLocalHost()、getByName()、getByAddress()等来生成InetAddress类的实质。  

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
Console结果输出显示：  

    本机IP地址字符串:192.168.1.107  
    本机用户名:andieguo  
    本机主机名:andieguo/192.168.1.107  

### 4、URL访问Internet ###

有两种方法可以用来访问Internet。一是利用URL类的openStream()方法；二是使用openConnection()方法创建一个URLConnection类的对象。其中，方法openStream()与指定的URL建立连接并返回InputStream类的对象，以从这一连接中读取数据。

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


### 参考： ###

1、[JAVA之URL](http://www.blogjava.net/baoyaer/articles/120422.html)  
2、[Java获取URL对应的资源](http://lavasoft.blog.51cto.com/62575/120445/)