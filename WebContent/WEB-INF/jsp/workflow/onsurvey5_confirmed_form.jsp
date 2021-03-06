﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<script>
		function forConfirm() {
			var frm =document.getElementById('itemForm');
			if(window.confirm("确定要提交?")){
				frm.action="${root}/workflow/onsurvey5_confirmed!submit.do";  
			 	frm.submit();
			}
			
		}
	</script>

</html:pageHead>
<body>
<html:pagetitle title="提交领导审批调研报告"/>
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="onsurvey5_confirmed!submit.do" method="post" enctype="multipart/form-data">
<s:hidden name="item.id" value="%{item.id}" />

<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:property value="%{item.itemName}"/> &nbsp;
			<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>"> 查看项目详细</a>&nbsp;		 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">通知标题</td>
        <td class="td_edit">		
			<s:property value="%{item.surveyInfoTitle}"/> 
        </td>
    </tr>

    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
			<s:property value="%{item.surveyInfoBody}" escapeHtml="false"/>
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">签收信息</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="mktSignApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				签收人:<s:property  value="approverName"/> &nbsp;&nbsp;
				是否签收:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				签收意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
     <tr>
    	<td height="24" align="right" class="td_lable">调研报告日期</td>
        <td colspan="3" class="td_edit">
       		<s:property value="%{item.surveyReportDate}" />
        </td>
    </tr>
     <tr>
    	<td height="24" align="right" class="td_lable">调研报告附件</td>
        <td colspan="3" class="td_edit">
       		<a href="${root}/oa/download!downloadReport.do?item.id=${item.id}">下载调研报告</a>
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">确认信息</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="mktConfirmApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				签收人:<s:property  value="approverName"/> &nbsp;&nbsp;
				是否签收:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				签收意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">领导审批信息</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="rptApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				签收人:<s:property  value="approverName"/> &nbsp;&nbsp;
				是否签收:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				签收意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">领导审批</td>
        <td class="td_edit">		
			<html:person idName="useridStr"  nameName="userNameStr" height="50"/>		
        </td>
    </tr>
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <input type="button" onclick="forConfirm()"  value="提交领导审批">
       <input type="button" onclick="location.href='${root}/workflow/onsurvey.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>

    
</s:form>
</body>
</html>