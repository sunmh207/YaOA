﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<head>
<link rel="stylesheet" href="${root}/css/redmond/jquery-ui-1.10.3.custom.css" />
<link rel="stylesheet" href="${root}/css/style.css" />

<script type="text/javascript" src="${root}/js/jquery-1.9.1.js"></script>  
<script type="text/javascript" src="${root}/js/jquery-ui-1.10.3.custom.js"></script> 
<script type="text/javascript" src="${root}/js/jtmobile.js"></script>


<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script charset="utf-8" src="../lang/zh_CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>

<script>
	function forConfirm() {
		var frm =document.getElementById('itemForm');
		if(window.confirm("确定提交?")){
			frm.action="${root}/workflow/onsurvey4_pendingconfirm!confirm.do";  
		 	frm.submit();
		}
		
	}
	function forReject() {
		var frm =document.getElementById('itemForm');
		if(window.confirm("确定提交?")){
			frm.action="${root}/workflow/onsurvey4_pendingconfirm!reject.do";  
		 	frm.submit();
		}
		
	}
</script>

</head>
<body>
<html:pagetitle title="确认调研报告"/>
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="onsurvey4_pendingconfirm!confirm.do" method="post">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="itemApprove.id" value="%{itemApprove.id}" />

<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:property value="%{item.itemName}"/> &nbsp;
			<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>"> 查看项目详细</a>&nbsp;		 
        </td>
    </tr>
     <tr>
    	<td height="24" align="right" class="td_lable">调研报告日期</td>
        <td colspan="3" class="td_edit">
       		<s:property value="%{item.surveyReportDate}"   />
        </td>
    </tr>
     <tr>
    	<td height="24" align="right" class="td_lable">报告摘要</td>
        <td colspan="3" class="td_edit">
       		<s:property  value="%{item.surveyReportSummary}" />
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">调研报告</td>
        <td class="td_edit">		
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
        <td height="24" align="right" class="td_lable">意见</td>
        <td class="td_edit" colspan="3">		
			<s:textarea name="confirmComments"  value="%{confirmComments}" rows="3" cols="80"/> 	 
        </td>
    </tr> 
    
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <input type="button" onclick="forConfirm()"  value="同意">
       <input type="button" onclick="forReject()"  value="不同意">
       <input type="button" onclick="location.href='${root}/workflow/onsurvey.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>

    
</s:form>
</body>
</html>