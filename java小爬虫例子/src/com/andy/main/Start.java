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
			
			System.out.println("主线程休眠2秒钟");
			Thread.sleep(2000);
			
			// 启动抓取页面链接中的具体的职位链接
			System.out.println("启动抓取页面链接中的具体的职位链接");
			new GrabJob().start();
			
			System.out.println("主线程休眠2秒钟");
			Thread.sleep(2000);
			
			new GrabJobInfo().start();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
