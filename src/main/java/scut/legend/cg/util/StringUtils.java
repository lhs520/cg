package scut.legend.cg.util;

public class StringUtils {
	public static boolean isEmpty(String s){
		if(s==null){
			return true;
		}
		s=s.trim();
		return "".equals(s);
	}
	
	
}
