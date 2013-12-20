<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<script type="text/javascript">
function forApprove() {
	var frm =document.getElementById('itemdetailform');
	frm.action="${root}/oa/itemdetail_leader1!approve.do";  
 	frm.submit();
}
function forDisapprove() {
	var frm =document.getElementById('itemdetailform');
	frm.action="${root}/oa/itemdetail_leader1!disapprove.do";  
 	frm.submit();
}
</script>
<body>
<s:actionerror />
<s:actionmessage />
<s:form id="itemdetailform" method="post">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="item.requesterId" value="%{item.requesterId}" />
<s:hidden name="item.requesterName" value="%{item.requesterName}" />
<s:hidden name="item.applyDate" value="%{item.applyDate}" />
	
<s:hidden name="itemOperator.id" value="%{itemOperator.id}" />	
	
<table width="100%" class="table_portalet" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td align="left" class='td_portalet'>项目详细</td>
	</tr>
</table>
<br>		
<table width="101%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">项目</td>
        <td class="td_edit">		
			<s:label  value="%{item.itemName}" /> 			 
        </td>
    </tr>

    <tr>
        <td height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
			<s:label  value="%{item.description}" /> 			 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">状态</td>
        <td class="td_edit">		
			<s:label  value="%{item.statusTXT}" /> 			 
        </td>
    </tr>
</table>
<br>

<table width="100%" class="table_portalet" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td align="left" class='td_portalet'>领导审批</td>
	</tr>
</table>
<br>
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">意见</td>
        <td class="td_edit">		
			<s:textarea  name="approvalMsg" value="%{approvalMsg}" rows="4" cols="80"/> 			 
        </td>
    </tr>
    <tr>
        <td class="td_edit" colspan="2">		
			 <input name="add" type="button" value="同意实施" onClick="forApprove()"> 			 
			 <input name="add" type="button" value="不同意实施" onClick="forDisapprove()"> 			 
        </td>
    </tr>
</table>    
    
    
</s:form>
</body>
</html>