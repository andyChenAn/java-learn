package com.andy.jobList;

import java.util.ArrayList;

public class JobList {
	public static ArrayList<String> jobList = new ArrayList<String>();
	public static synchronized void add (String pageUrl) {
		jobList.add(pageUrl);
	}
	public static String get () {
		return jobList.remove(0);
	}
}
