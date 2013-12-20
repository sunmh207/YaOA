<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jitong.JitongConstants" %>
<%@ page import="com.jitong.common.util.DBtools" %>
<%@ page import="com.jitong.common.service.DefaultBaseService" %>
<%@ page import="com.jitong.common.util.FileUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.io.File" %>
<%@ page import="java.sql.Blob" %>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%
	 String url = "";
	 Blob blob = (Blob) session.getAttribute("BLOB");
	 String  ext = (String) session.getAttribute("EXT");
	 if(blob!=null){
	     String path = application.getRealPath("/") + File.separator + "file" + File.separator + "tmp";
	     String fileName = FileUtil.exportFile(path, ext, blob, true);
	     url = request.getContextPath() + "/file/tmp/" + fileName;
	     if(com.jitong.common.util.FileTypeUtil.isImage(com.jitong.common.util.StringUtil.fillNull(ext))){
	    	 out.print("<img src='"+url+"'>"); 
	     }else{
	     	response.sendRedirect(url);
	     }
     }else{
    	 out.println("没有附件 &nbsp;&nbsp; <input type='button' value='返回' onclick='window.history.go(-1);'> ");
     }

%>