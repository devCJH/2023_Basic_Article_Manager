package com.koreaIT.java.BAM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getDate() {
		
		Date date = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

		return formatter.format(date);
	}
}
