package cg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scut.legend.cg.util.TimeUtils;


public class TestPath {
	
	public static void main(String[] args) throws ParseException {
//		Calendar calendar=Calendar.getInstance();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd:HHmmss");
//		calendar.setTime(sdf.parse("20170621:000000"));
//		System.out.println(calendar.getTimeInMillis());
//		calendar.add(Calendar.DATE, 1);
//		System.out.println(sdf.format(calendar.getTime()));
		System.out.println(TimeUtils.parseD("20170625"));
//		Pattern p=Pattern.compile("[0-9]{17}[0-9xX]");
//		Matcher matcher=p.matcher(null);
//		System.out.println(matcher.matches());
	}
}
