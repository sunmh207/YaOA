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
			var editor1 = K.create('textarea[name="item.surveyInfoBody"]', {
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
		
		function info() {
			var frm =document.getElementById('itemForm');
			if(frm.useridStr.value==null||frm.useridStr.value==""){
				alert("请选择被通知人员！");
				return false;
			}
			frm.action="${root}/workflow/onsurvey1_pendinginfo!info.do";  
		 	frm.submit();
		}
	</script>

</head>
<body>
<html:pagetitle title="发送调研通知"/>
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="itempendingsurveyinput!save.do" method="post">
<s:hidden name="item.id" value="%{item.id}" />

<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">通知标题</td>
        <td class="td_edit">		
			<s:textfield  name="item.surveyInfoTitle" value="%{item.surveyInfoTitle}" size="80"/> 
			<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>"> 查看项目详细</a>&nbsp;		 
        </td>
    </tr>

    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
			<s:textarea name="item.surveyInfoBody" value="%{item.surveyInfoBody}"  cols="100" rows="12" style="width:700px;height:300px;visibility:hidden;"/>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">接收通知人</td>
        <td class="td_edit">		
			<html:person idName="useridStr"  nameName="userNameStr" height="50"/>		
        </td>
    </tr>
    
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <input type="button" onclick="info()"  value="发送通知">
       <input type="button" onclick="location.href='${root}/workflow/onsurvey.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>

    
</s:form>
</body>
</html>