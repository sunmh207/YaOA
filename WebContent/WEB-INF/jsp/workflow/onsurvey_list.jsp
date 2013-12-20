<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<body>
<center>
<html:pagetitle title="待发调研通知"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>
	<s:iterator value="pendingInfoList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"5_PENDING_INFO\"">
				<a href="${root}/workflow/onsurvey1_pendinginfo!input.do?item.id=<s:property value="id"/>">编辑调研通知</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
		</s:iterator>
</table>

<html:pagetitle title="待签收"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="pendingMKTSignList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTSignList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTSignList[#st.index][0].requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTSignList[#st.index][0].applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTSignList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="pendingMKTSignList[#st.index][1].status == \"PENDING\" && pendingMKTSignList[#st.index][1].type == \"MKT_SIGN\"">
				<a href="${root}/workflow/onsurvey2_pendingsign!input.do?item.id=<s:property  value="pendingMKTSignList[#st.index][0].id"/>&itemApprove.id=<s:property  value="pendingMKTSignList[#st.index][1].id"/>">进入签收页面</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="pendingMKTSignList[#st.index][0].id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>	
</table>
<html:pagetitle title="已签收,待上传调研报告"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="pendingMKTSignedList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"5_MKT_SIGNED\" ">
				<a href="${root}/workflow/onsurvey3_signed!input.do?item.id=<s:property value="id"/>">上传调研报告</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
<html:pagetitle title="待确认"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>
	<s:iterator value="pendingMKTConfirmList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTConfirmList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTConfirmList[#st.index][0].requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTConfirmList[#st.index][0].applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingMKTConfirmList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="pendingMKTConfirmList[#st.index][1].status == \"PENDING\" && pendingMKTConfirmList[#st.index][1].type == \"MKT_CONFIRM\"">
				<a href="${root}/workflow/onsurvey4_pendingconfirm!input.do?item.id=<s:property  value="pendingMKTConfirmList[#st.index][0].id"/>&itemApprove.id=<s:property  value="pendingMKTConfirmList[#st.index][1].id"/>">进入确认界面</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="pendingMKTConfirmList[#st.index][0].id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>		
</table>
<html:pagetitle title="已确认"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="mktConfirmedList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"5_MKT_CONFIRMED\" || status == \"6_MKT_RPT_REJECT\" ">
				<a href="${root}/workflow/onsurvey5_confirmed!input.do?item.id=<s:property value="id"/>">编辑</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>

</center>
</body>
</html>