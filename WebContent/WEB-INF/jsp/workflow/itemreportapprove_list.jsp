﻿
<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<body>
<center>
<html:pagetitle title="报告审批"/>
<%-- <form id="searchForm"　action="${root}/workflow/mynewitems.do">
<html:pagination exportExcel="true"/>
</form> --%>

<table width="101%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header" width="60">操作</td>
	</tr>
	<s:iterator value="objectList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="objectList[#st.index][1].status == \"PENDING\"">
				<a href="${root}/workflow/itemreportapproveinput!input.do?item.id=<s:property  value="objectList[#st.index][0].id"/>&itemApprove.id=<s:property  value="objectList[#st.index][1].id"/>">审批</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="objectList[#st.index][0].id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
		</s:iterator>
</table>
</center>
</body>
</html>