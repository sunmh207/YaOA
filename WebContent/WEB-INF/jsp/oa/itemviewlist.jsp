﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<script type="text/javascript">
function fSubmit(){
	return window.confirm('确认提交领导审核？'); 
} 

</script>
<body>
<center>
<form id="searchForm"　action="${root}/oa/itemview.do">
<div style="float: left; padding-bottom: 0px;">
项目名称 <input name="qryItemName" value='${qryItemName}' size="5">&nbsp;&nbsp;
分类<s:select  name="qryItemType" list="#{'物资管理':'物资管理', '工程建设':'工程建设'}"  headerKey="" headerValue="--请选择--"/>
状态<s:select  name="qryStatus" list="statusMap" headerKey="" headerValue="--请选择--"/>
立项时间 <input name="qryApplyDateStart" value='${qryApplyDateStart}' class="date"> ~ <input name="qryApplyDateEnd" value='${qryApplyDateEnd}' class="date">&nbsp;&nbsp;
立项部门<input name="qryHostDept" value='${qryHostDept}'>&nbsp;&nbsp;
<input type="button" value="搜索" onclick="javascript:goPage(1)"> 
</div>
<html:pagination exportExcel="true"/>

</form>

<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">项目名称</td>
		<td class="td_header">分类</td>
		<td class="td_header">立项时间</td>
		<td class="td_header">立项部门</td>
		
		<td class="td_header">立项依据</td>
		<td class="td_header">投入资金</td>
		<td class="td_header">资金来源</td>
		<td class="td_header">招标形式</td>
		
		<td class="td_header">提交人</td>
		<td class="td_header">提交日期</td>
		<td class="td_header">状态</td>
	</tr>
	<s:iterator value="objectList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].itemName"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].itemType"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][1].applyDate"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][1].hostDept"/></td>
			
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][1].refNumber"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][1].planAmount"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][1].moneySource"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][1].bidType"/></td>

			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].requesterName"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].applyDate"/></td>
			<td align="left" class='td_body'> <s:property  value="objectList[#st.index][0].statusTXT"/></td>
			
		</tr>
		</s:iterator>
</table>
</center>
</body>
</html>