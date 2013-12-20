<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<script type="text/javascript">
function approve() {
	var frm =document.getElementById('itemForm');
	if(window.confirm("确定要提交?")){
		frm.action="${root}/workflow/onfinish2!approve.do";  
 		frm.submit();
	}
}
function reject() {
	var frm =document.getElementById('itemForm');
	if(window.confirm("确定要提交?")){
		frm.action="${root}/workflow/onfinish2!reject.do";  
 		frm.submit();
	}
}
</script>
</html:pageHead>
<body>
<html:pagetitle title="结项->领导审批"/>

<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="onfinish2!submit.do" method="post"  enctype="multipart/form-data">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="itemApprove.id" value="%{itemApprove.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">结项名称</td>
        <td class="td_edit">		
			<s:property value="%{item.finishItemName}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">投资金额</td>
        <td class="td_edit">		
			<s:property value="%{item.investAmount}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">标   底</td>
        <td class="td_edit">		
			<s:property  value="%{item.biaodiAmount}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">中 标 价</td>
        <td class="td_edit">		
			<s:property value="%{item.zhongbiaoPrice}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">中标单位</td>
        <td class="td_edit">		
			<s:property value="%{item.zhongbiaoCompany}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">招竞标时间</td>
        <td class="td_edit">		
			<s:property  value="%{item.jingbiaoDate}" /> 	 
        </td>
    </tr> 
    <tr>
        <td height="24" align="right" class="td_lable">参加招 标人员</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{item.bidParticipants}" />
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">完成工期或购销时间</td>
        <td class="td_edit">		
			<s:property  value="%{item.finishDate}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">合同签订时间</td>
        <td class="td_edit">		
			<s:property   value="%{item.contractSignDate}"  /> 	 
        </td>
    </tr> 
    <tr>
        <td height="24" align="right" class="td_lable">主办部门负责人</td>
        <td class="td_edit">		
			<s:property value="%{item.sponsorDeptRespPers}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">经办人</td>
        <td class="td_edit">		
			<s:property value="%{item.jingbanPers}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">结项主要内容</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{item.finishSummary}" />
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">主管领导审批意见</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{item.leadComments}" />
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">主管领导审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="leadApproveList" status="st">
				[<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
    
    <tr>
        <td height="24" align="right" class="td_lable">纪检监察机关意见</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{item.jjwComments}" />
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">主管领导审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="jjwApproveList" status="st">
				[<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">备注</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{item.finishNote}" />
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">主管领导审批意见</td>
        <td class="td_edit" colspan="3">		
			<s:textarea name="item.leadComments" value="%{item.leadComments}"  cols="80" rows="3"/>
        </td>
    </tr>

    
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
      <input type="button" onclick="approve()"  value="同意">
      <input type="button" onclick="reject()"  value="不同意">
       <input type="button" onclick="location.href='${root}/workflow/onfinish.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>