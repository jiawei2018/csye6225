package com.csye6225.fall2018.project.jw.cloudProject.service;

import com.fasterxml.jackson.core.io.SegmentedStringWriter;

public class idGenerater {
//	public static String generateId(String who, int all) {
//			String res =  String.format("%s%06d", who , all);
//		return res;
//	}

	public static String generateId(String prefix) {//shortone
		String res1 = String.format("%s%03d", prefix , (int) (Math.random()*100000));
		return res1;
	}
	public static String generateId(String prefix, int seed) {
		if(seed > 9) {
			seed = 9;
		}
		if(seed < 3) {
			seed = 3;
		}
		String para = "%s%0"+ seed + "d" ;
		String res = String.format(para, prefix , (int) (Math.random()*getseed(seed)));
		return res;
		
	}
	
	private static int getseed(int seed) {
		if(seed > 9) {
			seed = 9;
		}
		if(seed < 3) {
			seed = 3;
		}
		int res = (int)Math.pow(10, seed);
		return res;
	}
}
