package com.rhb.ginkgo.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Convert {
	public static String t2s(Timestamp timestamp,String pattern){
		String str;
		if(timestamp==null){
			str = "";
		}else{
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			str = df.format(timestamp);
		}
		return str;
	}
	
	public static String html2Str(String html){
		String reg1 = "<[^>]*>";
		String reg2 = "&quot;";

		String s = html.replaceAll(reg1, "").replaceAll(reg2, "\"");
		return s;
		
	}
	
}
