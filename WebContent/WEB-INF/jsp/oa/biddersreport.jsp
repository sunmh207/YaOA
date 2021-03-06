﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead/>
<script type="text/javascript">

function exportExcel() {
	var frm =document.getElementById('itemForm');
	frm.action='${root}/oa/itemdetail!exportBiddersReport.do?item.id=<%=request.getParameter("item.id")%>' ; 
		
	frm.submit();
	}
</script>	

<body>
<html:pagetitle title="竞标监督登记表"/>
<input type="button" value="返回" onclick="window.history.go(-1);"> 
<!-- <input type="button" value="导出Excel" onclick="exportExcel()">  -->
<input type="button" onclick="location.href='${root}/oa/exportword!exportSupervision.do?item.id=<s:property value="item.id"/>'" value="导出Word"> 	

<center>
<s:form id="itemForm" action=""  method="post" >
</s:form>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">参加投标单位</td>
		<td class="td_header">企业资质等级和证号</td>
		<td class="td_header">营业执照/注 册 号</td>
		<td class="td_header">法人代表</td>
		<td class="td_header">委托人姓名</td>
		<td class="td_header">委托人身份证</td>
		<td class="td_header">第一次报价</td>
		<td class="td_header">第二次报价</td>
		<td class="td_header">第三次报价</td>
		<td class="td_header">得分</td>
	</tr>
	<s:iterator value="recommendBidderList" status="st">
		<tr>
			<td align="left" class='td_body'><s:property value="name"/></td>
			<td align="left" class='td_body'><s:property value="qiyezizhi"/></td>
			<td align="left" class='td_body'><s:property value="yingyezhizhao"/></td>
			<td align="left" class='td_body'><s:property value="farendaibiaoName"/></td>
			<td align="left" class='td_body'><s:property value="weituorenName"/></td>
			<td align="left" class='td_body'><s:property value="weituorenId"/></td>
			<td align="left" class='td_body'><s:property value="baojia1"/></td>
			<td align="left" class='td_body'><s:property value="baojia2"/></td>
			<td align="left" class='td_body'><s:property value="baojia3"/></td>
			<td align="left" class='td_body'><s:property value="score"/></td>
		</tr>
		</s:iterator>
</table>
</center>
</body>
</html>