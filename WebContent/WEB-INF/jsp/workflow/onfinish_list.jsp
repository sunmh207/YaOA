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
		<td class="td_header">项目名称</td>
		<td class="td_header">提交人</td>
		<td class="td_header">项目提交日期</td>
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
			<s:if test="status == \"11_ON_FINISH\" ">
				<a href="${root}/workflow/onfinish1!input.do?item.id=<s:property value="id"/>">新增结项表</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>	
<br>
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
<s:iterator value="forSponsorHQLRejectList" status="st">
	<tr>
	<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
		<td align="left" class='td_body'><s:property value="finishItemName"/></td>
		<td align="left" class='td_body'><s:property value="zhongbiaoCompany"/></td>
		<td align="left" class='td_body'><s:property value="zhongbiaoPrice"/></td>
		<td align="left" class='td_body'><s:property value="finishDate"/></td>
		<td align="left" class='td_body'><s:property value="item.statusTXT"/>-><s:property value="statusTXT"/></td>
		<td align="left" class='td_body'>
		<s:if test="status == \"11_ON_FINISH_LEAD_REJECT\" || status == \"11_ON_FINISH_JJW_REJECT\"">
		<a href="${root}/workflow/onfinish1!input.do?item.id=<s:property value="item.id"/>&itemFinish.id=<s:property value="id"/>">编辑结项表</a>&nbsp;
		</s:if>
		   <a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">详细</a>&nbsp;
		</td>
	</tr>
</s:iterator>
</table>

<html:pagetitle title="主管领导审批(主管领导)"/>
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
	<s:iterator value="forLeadList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].finishItemName"/></td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].zhongbiaoCompany"/> </td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].zhongbiaoPrice"/> </td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].finishDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="forLeadList[#st.index][0].item.statusTXT"/>-> <s:property  value="forLeadList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="forLeadList[#st.index][1].status == \"PENDING\" && forLeadList[#st.index][1].type == \"ONFINISH_LEAD_APPROVE\"">
				<a href="${root}/workflow/onfinish2!input.do?itemFinish.id=<s:property  value="forLeadList[#st.index][0].id"/>&itemApprove.id=<s:property  value="forLeadList[#st.index][1].id"/>">审批</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="forLeadList[#st.index][0].item.id"/>"> 详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>	
</table>
<html:pagetitle title="审批通过(发起人)"/>
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
	<s:iterator value="forSponsorList2" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="finishItemName"/></td>
			<td align="left" class='td_body'><s:property value="zhongbiaoCompany"/></td>
			<td align="left" class='td_body'><s:property value="zhongbiaoPrice"/></td>
			<td align="left" class='td_body'><s:property value="finishDate"/></td>
			<td align="left" class='td_body'><s:property value="item.statusTXT"/>-><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			<s:if test="status == \"11_ON_FINISH_LEAD_APPROVED\" ">
				<a href="${root}/workflow/onfinish3!input.do?itemFinish.id=<s:property value="id"/>">编辑</a>&nbsp;
			</s:if>	
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
<html:pagetitle title="待纪检委审批(纪检委)"/>
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
	<s:iterator value="forJJWList" status="st">
		<tr>
			<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].finishItemName"/></td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].zhongbiaoCompany"/> </td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].zhongbiaoPrice"/> </td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].finishDate"/> </td>
			<td align="left" class='td_body'> <s:property  value="forJJWList[#st.index][0].item.statusTXT"/>-> <s:property  value="forJJWList[#st.index][0].statusTXT"/></td>
			<td align="center" class='td_body'>
			<s:if test="forJJWList[#st.index][1].status == \"PENDING\" && forJJWList[#st.index][1].type == \"ONFINISH_JJW_APPROVE\"">
				<a href="${root}/workflow/onfinish4!input.do?itemFinish.id=<s:property  value="forJJWList[#st.index][0].id"/>&itemApprove.id=<s:property  value="forJJWList[#st.index][1].id"/>">审批</a>&nbsp;
			</s:if>
				<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="forJJWList[#st.index][0].item.id"/>"> 详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>		
</table>

<html:pagetitle title="纪检委审批通过(发起人)"/>
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
	<s:iterator value="forSponsorList3" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'><s:property value="finishItemName"/></td>
			<td align="left" class='td_body'><s:property value="zhongbiaoCompany"/></td>
			<td align="left" class='td_body'><s:property value="zhongbiaoPrice"/></td>
			<td align="left" class='td_body'><s:property value="finishDate"/></td>
			<td align="left" class='td_body'><s:property value="item.statusTXT"/>-><s:property value="statusTXT"/></td>
			<td align="left" class='td_body'>
			   <a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">详细</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
</center>
</body>
</html>