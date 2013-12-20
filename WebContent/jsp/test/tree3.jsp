<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=request.getContextPath()%>/myapp.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/common.js"></script>
    <title>myapp</title>
    <sx:head debug="true" parseContent="true"/>
</head>

<sx:tabbedpanel id="projectTabContainer" useSelectedTabCookie="true">
    <sx:div label="Projects" href="projects-projects-tab.action" executeScripts="true" />
    <sx:div label="Project Details" href="projects-projectDetails-tab.action" executeScripts="true" />
</sx:tabbedpanel>

