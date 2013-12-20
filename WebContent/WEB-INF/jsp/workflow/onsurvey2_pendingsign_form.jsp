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
		
		function sign() {
			var frm =document.getElementById('itemForm');
			if(window.confirm("确定签收通知?")){
				frm.action="${root}/workflow/onsurvey2_pendingsign!sign.do";  
			 	frm.submit();
			}
			
		}
	</script>

</head>
<body>
<html:pagetitle title="发送调研通知"/>
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="onsurvey2_pendingsign!sign.do" method="post">
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
        <td height="24" align="right" class="td_lable">签收意见</td>
        <td class="td_edit" colspan="3">		
			<s:textarea name="signComments"  value="%{signComments}" rows="3" cols="80"/> 	 
        </td>
    </tr>
    
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <input type="button" onclick="sign()"  value="签收">
       <input type="button" onclick="location.href='${root}/workflow/onsurvey.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>

    
</s:form>
</body>
</html>