package com.andy.grab;

import java.util.HashMap;
import java.util.Map;

public class JobData {
	public static Map<String , Map<String , String>> jobData = new HashMap<>();
	public static synchronized void put (String str , Map map) {
		jobData.put(str , map);
	}
}
