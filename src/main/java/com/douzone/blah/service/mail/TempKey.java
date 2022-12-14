package com.douzone.blah.service.mail;

import java.util.Random;

public class TempKey {
	private boolean lowerCheck;
	private int size;
	
	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}
	
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		do {
			num = ran.nextInt(78) + 48;
			if((num >= 48&&num<=57)||(num>=65&&num<=90)||(num>=97&&num<=122)) {
				sb.append((char) num);
			} else {
				continue;
			}
		} while(sb.length()<size);
		
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}
	
}
