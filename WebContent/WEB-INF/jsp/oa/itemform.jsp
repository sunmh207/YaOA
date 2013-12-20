<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
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
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
<s:actionerror />
<s:actionmessage />
<s:form name="itemForm" action="iteminput!save.do" method="post">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="item.requesterId" value="%{item.requesterId}" />
<s:hidden name="item.requesterName" value="%{item.requesterName}" />
<s:hidden name="item.applyDate" value="%{item.applyDate}" />
	
<table width="101%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">标题</td>
        <td class="td_edit">		
			<s:textfield  name="item.itemName" value="%{item.itemName}" size="80"/> 			 
        </td>
    </tr>

    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
			<s:textarea name="item.description" value="%{item.description}"  cols="100" rows="15" style="width:700px;height:400px;visibility:hidden;"/>
        </td>
    </tr>
    
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
       <input type="button" onclick="location.href='${root}/oa/item.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>