package com.andy.grab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.andy.jobList.JobList;
import com.andy.pageList.PageList;

public class GrabJobInfo extends Thread {
	public void run () {
		while (true) {
			Map<String , String> map = new HashMap<>();
			if (!JobList.jobList.isEmpty()) {
				String pageUrl = JobList.get();
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
					Pattern p = Pattern.compile("<h5 class=\"process_title\">(.+?)</h5>(?:.*?)<i>(.+?)</i>(?:.*?)<span>(.+?)</span>");
					Matcher m = p.matcher(data);
					if (m.find()) {
						map.put("jobName" , m.group(1));
						map.put("cityName" , m.group(2));
						map.put("jobAge" , m.group(3));
					}
					JobData.put(pageUrl , map);
					System.out.println(JobData.jobData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
