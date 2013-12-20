<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%
	String personId = request.getParameter("personId");
	try {
		java.util.Set<com.jitong.console.domain.User> mgrSet = com.jitong.common.util.SysCache.personMgrMap.get(personId);
		if(mgrSet!=null){
			java.util.Iterator<com.jitong.console.domain.User> it= mgrSet.iterator();
			while(it.hasNext()){
				com.jitong.console.domain.User user =it.next();
				out.println("Name:"+user.getName()+", phoneNumber:"+user.getPhoneNumber()+"<br>");
			}
			out.println("共计"+mgrSet.size()+"人");
		}
			
	
	} catch (Exception e) {
		e.printStackTrace();
		out.println(e);
	}
%>

<%@page import="java.util.Iterator"%><br></br>


