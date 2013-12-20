<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<body>
<center>
<html:pagetitle title="招标文件"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>
	<s:iterator value="pendingBidList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"9_ON_BID\" || status == \"9_ON_BID_ZBDEPT_REJECT\" || status == \"9_ON_BID_JJW_REJECT\"">
				<a href="${root}/workflow/onbid1_biddoc!input.do?item.id=<s:property value="id"/>">编辑</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>

<html:pagetitle title="待招标管理部门审核"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="pendingZBDeptApproveList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingZBDeptApproveList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="pendingZBDeptApproveList[#st.index][0].requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingZBDeptApproveList[#st.index][0].applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingZBDeptApproveList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="pendingZBDeptApproveList[#st.index][1].status == \"PENDING\" && pendingZBDeptApproveList[#st.index][1].type == \"ZBDEPT_APPROVE\"">
				<a href="${root}/workflow/onbid2_zbdeptapprove!input.do?item.id=<s:property  value="pendingZBDeptApproveList[#st.index][0].id"/>&itemApprove.id=<s:property  value="pendingZBDeptApproveList[#st.index][1].id"/>">审批</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="pendingZBDeptApproveList[#st.index][0].id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
	<s:iterator value="forZBDeptViewList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>	
</table>
<html:pagetitle title="已通过招标管理部门审核"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="onbid3List" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"9_ON_BID_ZBDEPT_APPROVED\" ">
				<a href="${root}/workflow/onbid3!input.do?item.id=<s:property value="id"/>">编辑</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
<html:pagetitle title="待纪检委审核"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>
	<s:iterator value="pendingJJWApproveList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingJJWApproveList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="pendingJJWApproveList[#st.index][0].requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingJJWApproveList[#st.index][0].applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="pendingJJWApproveList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="pendingJJWApproveList[#st.index][1].status == \"PENDING\" && pendingJJWApproveList[#st.index][1].type == \"JJW_APPROVE\"">
				<a href="${root}/workflow/onbid4_jjwapprove!input.do?item.id=<s:property  value="pendingJJWApproveList[#st.index][0].id"/>&itemApprove.id=<s:property  value="pendingJJWApproveList[#st.index][1].id"/>">审批</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="pendingJJWApproveList[#st.index][0].id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>	
	<s:iterator value="forJJWViewList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>		
</table>
<html:pagetitle title="通过纪检委审核"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="onbid5List" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"9_ON_BID_JJW_APPROVED\" ">
				<a href="${root}/workflow/onbid5!input.do?item.id=<s:property value="id"/>">编辑</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
<html:pagetitle title="待输入招标会记录"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>
	<s:iterator value="pendingMeetingRecordList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE\" ">
				<a href="${root}/workflow/onbid6!input.do?item.id=<s:property value="id"/>">编辑</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">查看详细</a>&nbsp;
			</td>
		</tr>
		</s:iterator>
</table>
</center>
</body>
</html>