package utils;

import javax.servlet.jsp.JspWriter;

public class JSFunction {
	
	//alert 후 뒤로가기
	public static void alertBack(String msg, JspWriter out) {
		
		try {
			String script = "<script>"
					  	+ "alert('"+msg+"');"
					  	+ "history.back();"
					  + "</script>";
			out.print(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
