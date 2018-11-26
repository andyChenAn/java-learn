package com.andy.main;

import com.andy.grab.GrabJob;
import com.andy.grab.GrabJobInfo;
import com.andy.grab.GrabPage;

/**
 * 整个抓取启动类
 * @author andy
 *
 */
public class Start {
	public static void main (String[] args) {
		try {
			// 启动抓取页面链接线程
			System.out.println("启动抓取页面链接线程");
			new GrabPage().start();
			
			// 启动抓取页面链接中的具体的职位链接
			System.out.println("启动抓取页面链接中的具体的职位链接");
			new GrabJob().start();
			
			new GrabJobInfo().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
