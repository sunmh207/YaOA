
<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<body>
<center>
<form id="searchForm"　action="${root}/workflow/mynewitems.do">
<html:pagination exportExcel="true"/>

</form>

<table width="101%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">项目名称</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header" width="60">操作</td>
	</tr>
	<s:iterator value="objectList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="statusTXT"/></td>
			<td align="center" class='td_body'>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">查看详细</a>&nbsp;
			</td>
		</tr>
		</s:iterator>
</table>
</center>
</body>
</html>