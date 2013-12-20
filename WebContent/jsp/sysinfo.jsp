<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="com.jitong.common.util.SysCache"%>
<%	
StringBuffer s=new StringBuffer();
	for(int i=0;i<1000*100;i++){
		s.append("ABCDEFGHIJ");
	}
SysCache.G.add(s.toString());
out.print("ok."+SysCache.G.size());
%>

<br></br>
System info page.