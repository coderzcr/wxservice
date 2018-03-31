package com.zcr.util;

import java.io.File;

/**
 * 清理maven仓库
 * 
 * @author xuzhao
 *
 */
public class MavenClean {

	private static int emptyDirectoryNum = 0;
	private static int lastUpdateNum = 0;

	private int emptyNum = 0;

	/**
	 * 要清理的内容包括 1. 所有的.lastUpdated 结尾的文件 2. 清理之后的所有空文件夹
	 */

	public static void main(String[] args) {
		final String mavenRepositoryPath = "D:\\workspace\\maven\\repository";
		File f = new File(mavenRepositoryPath);
		MavenClean clean = new MavenClean();
		clean.cleanLastUpdate(f);
		clean.cleanEmptyDirectory(f);
		System.out.println("删除文件 " + lastUpdateNum + "个, 删除空文件夹 " + emptyDirectoryNum + "个");
		System.out.println(clean.emptyNum);
	}

	/**
	 * 删除所有.lastUpdated结尾的文件
	 * 
	 * @param f
	 */
	public void cleanLastUpdate(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				File[] fs = f.listFiles();
				for (File fItem : fs) {
					cleanLastUpdate(fItem);
				}
			}else if (f.getName().endsWith("lastUpdated")) {
				lastUpdateNum++;
				System.out.println("要删除的文件是：" + f.getAbsolutePath());
				f.delete();
			}
		}

	}

	/**
	 * 删除所有的空文件夹
	 * 
	 * @param f
	 */
	public int cleanEmptyDirectory(File f) {
		if (f != null && f.isDirectory()) {
			File[] fs = f.listFiles();
			if (fs.length != 0) {
				for (File fc : fs) {
					cleanEmptyDirectory(fc);
				}
			}
			if(f.listFiles().length == 0) {
				emptyDirectoryNum++;
				emptyNum++;
				System.out.println(f.getAbsolutePath() + " 是空文件夹被删除");
				f.delete();
			}

		}
		return emptyNum;

	}
}