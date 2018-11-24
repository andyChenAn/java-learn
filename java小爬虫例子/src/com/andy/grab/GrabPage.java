package com.andy.grab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.andy.pageList.PageList;

/**
 * 抓取线程，主要用来抓取网站的职位数据
 * @author andy
 * 
 */
public class GrabPage extends Thread {
	public void run () {
		// 1、获取需要抓取的网址
		// 2、发送请求获取网页数据
		// 3、通过正则表达式匹配获取需要抓取的职位的链接
		try {
			String url = "http://www.doctorjob.com.cn/public/J1006_T2/";
			URL URLObject = new URL(url);
			InputStream inputStream = URLObject.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder pageContent = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				pageContent.append(line);
			}
			reader.close();
			reader = null;
			String data = pageContent.toString();
			Pattern p = Pattern.compile("T2_P(\\d+)/'>末页</a>");
			Matcher m = p.matcher(data);
			int pageCount = 1;
			if (m.find()) {
				pageCount = Integer.parseInt(m.group(1));
			}
			System.out.println("总共有" + pageCount + "页的数据");
			for (int i = 1 ; i <= pageCount ; i++) {
				String pageUrl = "http://www.doctorjob.com.cn/public/J1006_T2_P" + i + "/";
				PageList.add(pageUrl);
			}
			System.out.println("每一页的链接都被存放在PageList中");
			System.out.println(PageList.pageList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
