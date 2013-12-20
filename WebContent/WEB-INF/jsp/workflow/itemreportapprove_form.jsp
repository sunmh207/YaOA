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
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="item.surveyReportBody"]', {
				cssPath : '../plugins/code/prettify.css',
				uploadJson : '../jsp/upload_json.jsp',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['itemForm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['itemForm'].submit();
					});
				},
				 afterBlur:function(){ 
	                    this.sync(); 
	             } 
			});
			prettyPrint();
		});
		
		function approve() {
			var frm =document.getElementById('itemForm');
			if(window.confirm("确定同意?")){
				frm.action="${root}/workflow/itemreportapproveinput!approve.do";  
			 	frm.submit();
			}
		}
		function reject() {
			var frm =document.getElementById('itemForm');
			if(window.confirm("确定不同意?")){
				frm.action="${root}/workflow/itemreportapproveinput!reject.do";  
			 	frm.submit();
			}
		}
	</script>

</head>
<body>
<html:pagetitle title="审批调研报告"/>
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="" method="post">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="itemApprove.id" value="%{itemApprove.id}" />

	<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">标题 </td>
        <td class="td_edit">		
			关于《<s:property  value="%{item.itemName}" />》的调研报告 &nbsp;&nbsp;		
			<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>"> 查看项目详细</a>&nbsp; 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">提报人 </td>
        <td class="td_edit">		
			<s:property  value="%{item.surveyReportReporterName}" />	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">提报日期</td>
        <td class="td_edit">		
			<s:property  value="%{item.surveyReportDate}" />	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">报告摘要 </td>
        <td class="td_edit">		
			<s:property  value="%{item.surveyReportSummary}" />		 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
        <a href="${root}/oa/download!downloadReport.do?item.id=${item.id}">下载调研报告</a>
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">审批意见</td>
        <td class="td_edit">		
			<s:textarea name="itemApprove.comments" value="%{itemApprove.comments}"  cols="80" rows="3"/>
        </td>
    </tr>
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <input type="button" onclick="approve()"  value="同意">
       <input type="button" onclick="reject()"  value="不同意">
       <input type="button" onclick="location.href='${root}/workflow/itemreportapprove.do'" value="返回">
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
    
</s:form>
</body>
</html>