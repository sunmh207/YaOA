<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%
	String obj = request.getParameter("obj");
	try {
		if("User".equals(obj)){
			com.jitong.common.util.SysCache.loadUserMap();
			out.println("User reload successfully");
		}else if("PhoneUser".equals(obj)){
			com.jitong.common.util.SysCache.loadPhoneUserMap();
			out.println("PhoneUser reload successfully");
		}else if("Role".equals(obj)){
			com.jitong.common.util.SysCache.loadRoleMap();
			out.println("Role reload successfully");
		}else if("Menu".equals(obj)){
			com.jitong.common.util.SysCache.loadModuleMenuMap();
			out.println("Menu reload successfully");
		}else{
			out.println("Nothing done!");
		}
	} catch (Exception e) {
		e.printStackTrace();
		out.println(e);
	}
%>
<br></br>
<a href="${root}/jsp/syscache.jsp?obj=User">Reload User</a><br>
<a href="${root}/jsp/syscache.jsp?obj=PhoneUser">Phone User</a><br>
<a href="${root}/jsp/syscache.jsp?obj=Role">Reload Role</a><br>
<a href="${root}/jsp/syscache.jsp?obj=Menu">Reload Menu</a><br>
