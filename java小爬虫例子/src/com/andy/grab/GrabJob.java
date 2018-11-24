package com.andy.grab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.andy.jobList.JobList;
import com.andy.pageList.PageList;

/**
 * 抓取指定的页面中的所有的职位链接数据
 * @author andy
 *
 */
public class GrabJob extends Thread {
	public void run () {
		while (true) {
			if (!PageList.pageList.isEmpty()) {
				String pageUrl = PageList.get();
				try {
					URL URLObject = new URL(pageUrl);
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
					Pattern p = Pattern.compile("<a href=\"/resume/util/p(\\d+).html\" style=\"(?:.*?)\" target=_blank>(.+?)</a>");
					Matcher m = p.matcher(data);
					while (m.find()) {
						int jobNumber = Integer.parseInt(m.group(1));
						String jobUrl = "http://www.doctorjob.com.cn/resume/util/p" + jobNumber + ".html";
						JobList.add(jobUrl);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
		}
	}
}
