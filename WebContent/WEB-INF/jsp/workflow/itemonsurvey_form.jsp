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
		function submitReport() {
			var frm =document.getElementById('itemForm');
			if(frm.useridStr.value==null||frm.useridStr.value==""){
				alert("请指定审批领导！");
				return false;
			}
			frm.action="${root}/workflow/itemonsurveyinput!submitReport.do";  
		 	frm.submit();
		}
	</script>

	<script type="text/javascript">
	 $(function() {
		    $( "#tabs" ).tabs();
	});
	</script>
</head>
<body>
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="itempendingsurveyinput!save.do" method="post" enctype="multipart/form-data">

<s:hidden name="item.id" value="%{item.id}" />
<div id="tabs">
  <ul>
  	<li><a href="#tabs-3">调研报告</a></li>
    <li><a href="#tabs-2">调研通知</a></li>
  </ul>
  
  <div id="tabs-2">
    <p>
	<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">通知标题</td>
        <td class="td_edit">		
			<s:property escapeHtml="false" value="%{item.surveyInfoTitle}" /> 	
			<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">项目详细信息</a>&nbsp;	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">发送日期</td>
        <td class="td_edit">		
			<s:property escapeHtml="false" value="%{item.surveyInfoDate}" /> 		 
        </td>
    </tr>
 	<tr>
        <td height="24" align="right" class="td_lable">接收人</td>
        <td class="td_edit">		
			<s:property escapeHtml="false" value="%{item.surveyInfoReceiverNames}" /> 		 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
			<s:property escapeHtml="false" value="%{item.surveyInfoBody}" /> 
        </td>
    </tr>
	</table>
	</p>
  </div>
    
<div id="tabs-3">
    <p>
	<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">标题 </td>
        <td class="td_edit">		
			关于《<s:property  value="%{item.itemName}" />》的调研报告 		&nbsp;&nbsp;
			<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">项目详细信息</a>&nbsp; 
        </td>
    </tr>
 <tr>
        <td height="24" align="right" class="td_lable">报告摘要</td>
        <td class="td_edit">		
			<s:textarea name="item.surveyReportSummary" value="%{item.surveyReportSummary}" cols="80" rows="4"/> 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">调研报告附件</td>
        <td class="td_edit">		
			<s:file name="uploadfile"></s:file>
			
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">审核领导</td>
        <td class="td_edit">		
			<html:person idName="useridStr"  nameName="userNameStr" height="50"/>		
        </td>
    </tr>
    
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <input type="button" onclick="submitReport()"  value="提交报告">
       <%-- <input type="button" onclick="location.href='${root}/workflow/itemonsurvey.do'" value="返回"> --%>
        <s:submit value="返回" name="redirectAction:itemonsurvey"/>
      </td>
      <td>&nbsp;</td>
    </tr>	
    <tr>
        <td height="24" align="right" class="td_lable">所有审批信息</td>
        <td class="td_edit">
         <s:iterator value="rptApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
	</table>
	</p>
  </div>
    
</s:form>
</body>
</html>