package com.andy.grab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 读取文件类
 * @author andy
 *
 */
public class FileGetter {
	public static String readFile (String filename) throws IOException {
		File file = new File(filename);
		FileInputStream fs = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fs));
		String result = reader.readLine();
		return result;
	}
}
