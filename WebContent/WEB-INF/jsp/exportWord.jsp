<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Random" %>
<%@ page import="com.jitong.JitongConstants" %>
<%@ page import="com.jitong.common.util.DBtools" %>
<%@ page import="com.jitong.common.service.DefaultBaseService" %>
<%@ page import="com.jitong.common.util.WordUtil" %>
<%@ page import="com.jitong.common.util.DateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.io.File" %>
<%@ page import="java.sql.Blob" %>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%
	 String url = "";
	 Map<String,String> dataMap = (Map<String,String>) session.getAttribute("dataMap");
	 String  template = (String) session.getAttribute("template");
	 
	 String inpath = application.getRealPath("/")  +template;
	 String wordname= DateUtil.getCurrentTime("yyyyMMddHHmmss") + new Random().nextInt(10000)+".doc";
	 String outpath = application.getRealPath("/") + "file" + File.separator + "tmp"+File.separator+wordname;
	 url = request.getContextPath() + "/file/tmp/" + wordname;
		 
	 System.out.println(inpath);
	 System.out.println(outpath);
	 System.out.println(url);
	 try{
	File fileIn = new File(inpath);
	File fileOut = new File(outpath);
	if (fileOut.exists()) {
		fileOut.delete();
	}
	if (!fileIn.exists()) {
		return;
	}
	System.out.println("start.....");
	new WordUtil().exportWordFile(inpath, outpath, dataMap);
	System.out.println("end.....");
	Thread.sleep(2000);
    response.sendRedirect(url);
	}catch(Exception e){
		 e.printStackTrace();
	}
%>