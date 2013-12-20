<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<head>
<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script charset="utf-8" src="../lang/zh_CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="item.description"]', {
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
		
		function submit4approve() {
			var frm =document.getElementById('itemForm');
			if(frm.useridStr.value==null||frm.useridStr.value==""){
				alert("请指定审批领导！");
				return false;
			}
			frm.action="${root}/workflow/mynewitemsinput!submit4approve.do";  
		 	frm.submit();
		}
	</script>
</head>
<body>
<html:pagetitle title="编辑项目"/>

<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="mynewitemsinput!save.do" method="post">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="item.requesterId" value="%{item.requesterId}" />
<s:hidden name="item.requesterName" value="%{item.requesterName}" />
<s:hidden name="item.applyDate" value="%{item.applyDate}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:textfield  name="item.itemName" value="%{item.itemName}" size="80"/> 			 
        </td>
    </tr>
 <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:select 	list="#{'物资管理':'物资管理', '工程建设':'工程建设'}"	 name="item.itemType"/>		 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
			<s:textarea name="item.description" value="%{item.description}"  cols="100" rows="15" style="width:700px;height:400px;visibility:hidden;"/>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">审批领导</td>
        <td class="td_edit">		
			<html:person idName="useridStr"  nameName="userNameStr" height="50"/>		
        </td>
    </tr>
     <tr>
    	<td height="24" align="right" class="td_lable">领导审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="itemApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr> 
    
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="保存"/>
       <input type="button" onclick="submit4approve()"  value="保存并提交领导审批">
       <input type="button" onclick="location.href='${root}/workflow/mynewitems.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>