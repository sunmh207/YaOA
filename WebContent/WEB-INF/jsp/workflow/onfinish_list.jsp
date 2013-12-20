<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<body>
<center>
<html:pagetitle title="填写结项表(发起人)"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>
	<s:iterator value="forSponsorList1" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"11_ON_FINISH\" || status == \"11_ON_FINISH_LEAD_REJECT\" || status == \"11_ON_FINISH_JJW_REJECT\" || status == \"10_ON_ACCEPT\" ">
				<a href="${root}/workflow/onfinish1!input.do?item.id=<s:property value="id"/>">填写结项表</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
		</s:iterator>
</table>

<html:pagetitle title="主管领导审批(主管领导)"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="forLeadList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="forLeadList[#st.index][1].status == \"PENDING\" && forLeadList[#st.index][1].type == \"ONFINISH_LEAD_APPROVE\"">
				<a href="${root}/workflow/onfinish2!input.do?item.id=<s:property  value="forLeadList[#st.index][0].id"/>&itemApprove.id=<s:property  value="forLeadList[#st.index][1].id"/>">审批</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="forLeadList[#st.index][0].id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>	
</table>
<html:pagetitle title="审批通过(发起人)"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="forSponsorList2" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"11_ON_FINISH_LEAD_APPROVED\" ">
				<a href="${root}/workflow/onfinish3!input.do?item.id=<s:property value="id"/>">编辑</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
<html:pagetitle title="待纪检委审批(纪检委)"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>
	<s:iterator value="forJJWList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].requesterName"/> </td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].applyDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="forJJWList[#st.index][1].status == \"PENDING\" && forJJWList[#st.index][1].type == \"ONFINISH_JJW_APPROVE\"">
				<a href="${root}/workflow/onfinish4!input.do?item.id=<s:property  value="forJJWList[#st.index][0].id"/>&itemApprove.id=<s:property  value="forJJWList[#st.index][1].id"/>">审批</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="forJJWList[#st.index][0].id"/>"> 查看详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>		
</table>

<html:pagetitle title="纪检委审批通过(发起人)"/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">标题</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
		<td class="td_header">操作</td>
	</tr>	
	<s:iterator value="forSponsorList3" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="itemName"/></td>
			<td align="left" class='td_body'><s:property value="requesterName"/></td>
			<td align="left" class='td_body'><s:property value="applyDate"/></td>
			<td align="left" class='td_body'><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			   <a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
</center>
</body>
</html>