
<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<script type="text/javascript">
function fSubmit(){
	return window.confirm('确认发送通知？'); 
} 

</script>
<body>
<center>
<html:pagetitle title="发起调研"/>
<%-- <form id="searchForm"　action="${root}/workflow/itempendingsurvey.do">
<div style="float: left; padding-bottom: 0px;">
项目名称 <input name="searchForm.itemName" value='${searchForm.itemName}' size="5">&nbsp;&nbsp;
<input type="button" value="搜索" onclick="javascript:goPage(1)"> 
</div>
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
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="status == \"4_PENDING_SURVEY\"">
				<a href="${root}/workflow/itempendingsurveyinput!input.do?item.id=<s:property value="id"/>">发通知</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
		</s:iterator>
</table>
</center>
</body>
</html>