this.getClass().getClassLoader().getResourceAsStream

[JAVA之URL](http://www.blogjava.net/baoyaer/articles/120422.html)

　　上例首先创建URL对象url，并在其基础上打开输入流获取InputStreamReader对象，再由此对象创建BufferedReader对象br，从br中读取数据即可得到url所指定的资源文件。
　　上面的openStream()方法只能读取网络资源，若要既能读取又能发送数据，则要用到URL类的openConnection()方法来创建一个 URLConnection类的对象，此对象在本地机和URL指定的远程节点建立一条HTTP协议的数据通道，可进行双向数据传输。
　　类URLConnection提供了很多设置和获取连接参数的方法，最常用到的是getInputStream()和getOutputStream()方法，如：
　　URL sum=new URL("http://java.sum.com/cgi-bin/backwards");
　　URLConnection suncon=buaa.openConnection();
　　sumcon.setDoOutput(true);
　　DataInputStream dis=new DataInputStream(suncon.getInputStream());
　　PrintStream ps=new PrintStream(suncon.getOutputStream());
　　String str=dis.readLine();
　　ps.println("来自客户机的信息：.......");