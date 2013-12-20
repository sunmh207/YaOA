<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<head>
	<script>
		function approve() {
			var frm =document.getElementById('itemForm');
			frm.action="${root}/workflow/itemapproveinput!approve.do";  
		 	frm.submit();
		}
		function reject() {
			var frm =document.getElementById('itemForm');
			frm.action="${root}/workflow/itemapproveinput!reject.do";  
		 	frm.submit();
		}
	</script>
</head>
<body>
<html:pagetitle title="审批项目"/>

<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="" method="post">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="itemApprove.id" value="%{itemApprove.id}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:label  name="item.itemName" /> 			 
        </td>
    </tr>

    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">
        <s:property escapeHtml="false" value="%{item.description}"/>	
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">是否需要调研</td>
        <td class="td_edit">
        <s:select name="needResearch" list="#{'1':'是', '0':'否'}" >
        </s:select>
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
       <input type="button" onclick="location.href='${root}/workflow/itemapprove.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
    <tr>
        <td height="24" align="right" class="td_lable">所有审批信息</td>
        <td class="td_edit">
         <s:iterator value="itemApproveList" status="st">
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