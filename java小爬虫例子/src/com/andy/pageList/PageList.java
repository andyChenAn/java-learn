package com.andy.pageList;

import java.util.ArrayList;

/**
 * 该类用于存放抓取的页面链接
 * @author andy
 *
 */
public class PageList {
	public static ArrayList<String> pageList = new ArrayList<String>();
	public static synchronized void add (String pageUrl) {
		pageList.add(pageUrl);
	}
	public static String get () {
		return pageList.remove(0);
	}
}
