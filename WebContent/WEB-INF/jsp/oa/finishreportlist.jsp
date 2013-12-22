<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<body>

<html:pagetitle title="结项表列表"/>
<input type="button" value="返回" onclick="window.history.go(-1);">
<center>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
<tr>
	<td class="td_header">序号</td>
	<td class="td_header">结项名称</td>
	<td class="td_header">中标单位</td>
	<td class="td_header">中标价</td>
	<td class="td_header">完成工期或购销时间</td>
	<td class="td_header">状态</td>
	<td class="td_header">操作</td>
</tr>		
<s:iterator value="itemFinishtList" status="st">
	<tr>
	<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
		<td align="left" class='td_body'><s:property value="finishItemName"/></td>
		<td align="left" class='td_body'><s:property value="zhongbiaoCompany"/></td>
		<td align="left" class='td_body'><s:property value="zhongbiaoPrice"/></td>
		<td align="left" class='td_body'><s:property value="finishDate"/></td>
		<td align="left" class='td_body'><s:property value="item.statusTXT"/>-><s:property value="statusTXT"/></td>
		<td align="left" class='td_body'>
		<a href="${root}/oa/itemdetail!finishReport.do?itemFinish.id=<s:property value="id"/>">查看结项表</a>&nbsp;
		   <a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">项目详细信息</a>&nbsp;
		</td>
	</tr>
</s:iterator>
</table>

</center>
</body>
</html>