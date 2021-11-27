package backjoon;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptUtils {
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
	}
	
	public static void alert(HttpServletResponse response, String alertMsg) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertMsg+"');</script>");
		out.flush();
	}
	
	//try에서 사용
	public static void alertAndMovePage(HttpServletResponse response, String alertMsg, String nextPage) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertMsg+"'); location.href='"+nextPage+"';</script>");
		out.flush();
	}
	
	//catch에서 사용
	public static void alertAndBackPage(HttpServletResponse response, String alertMsg) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertMsg+"'); history.go(-1);</script>");
		out.flush();
	}
}
