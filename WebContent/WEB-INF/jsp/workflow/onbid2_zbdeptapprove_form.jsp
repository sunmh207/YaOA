<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<script type="text/javascript">
function approve() {
	var frm =document.getElementById('bidForm');
	if(window.confirm("确定同意招标?")){
		frm.action="${root}/workflow/onbid2_zbdeptapprove!approve.do";  
	 	frm.submit();
	}
	
}
function reject() {
	var frm =document.getElementById('bidForm');
	if(window.confirm("确定不同意招标?")){
		frm.action="${root}/workflow/onbid2_zbdeptapprove!reject.do";  
	 	frm.submit();
	}
}

function setDefaultApproveComment() {
	var commentArea =document.getElementById('bidForm_bid_zhaobiaoDeptComments');
	commentArea.value="资质齐全，同意招标！";
}
function setDefaultRejectComment() {
	var commentArea =document.getElementById('bidForm_bid_zhaobiaoDeptComments');
	commentArea.value="资质不符合要求，不得招标！！";
}
</script>
</html:pageHead>
<body>
<html:pagetitle title="项目招标->招标部门审核"/>

<s:actionerror />
<s:actionmessage />
<s:form id="bidForm" action="" method="post">
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="itemApprove.id" value="%{itemApprove.id}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">主办部门</td>
        <td class="td_edit">		
			<s:property  value="%{bid.hostDept}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">日期</td>
        <td class="td_edit">		
			<s:property  value="%{bid.applyDate}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:property  value="%{bid.bidItemName}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">计划投资金额</td>
        <td class="td_edit">		
			<s:property  value="%{bid.planAmount}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">批准文号或立项依据</td>
        <td class="td_edit">		
			<s:property  value="%{bid.refNumber}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">资金来源</td>
        <td class="td_edit">		
			<s:property  value="%{bid.moneySource}"/> 	 
        </td>
    </tr>

     <tr>
        <td height="24" align="right" class="td_lable">主要工作项目及工作量、产品型号和规格</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.itemSummary}" />
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">计划竞标时间</td>
        <td class="td_edit">		
			<s:property value="%{bid.planBidDate}"  /> 	 
        </td>
        <td height="24" align="right" class="td_lable">经办人电话</td>
        <td class="td_edit">		
			<s:property value="%{bid.agentPersonPhone}"/> 	 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">推荐投标单位</td>
        <td class="td_edit" colspan="3">	
        	<s:iterator value="recommendBidderList" status="st">
        	<s:property value='#st.index+1'/>.<s:property value="name"/> &nbsp;
        	<a href="${root}/oa/download!downloadRecommendBidderDoc.do?recommendBidder.id=<s:property value="id"/>">下载附件</a>&nbsp;
        	<br>
        	</s:iterator> 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">主办单位(部门)意见</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.hostDeptComments}" />
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">备注</td>
        <td class="td_edit" colspan="3">	
        <a href="${root}/oa/download!downloadReport.do?item.id=${item.id}">下载调研报告</a>	
        <a href="${root}/oa/download!downloadBidDoc.do?item.id=${item.id}">下载招标文件</a>	
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">竞标时间</td>
        <td class="td_edit" colspan="3">		
			<s:textfield  name="bid.bidDate" value="%{bid.bidDate}"  cssClass="date" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">招竞标形式</td>
        <td class="td_edit">		
			<s:textfield  name="bid.bidType" value="%{bid.bidType}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">经办人</td>
        <td class="td_edit">		
			<s:textfield  name="bid.zhaobiaoDeptAgentPerson" value="%{bid.zhaobiaoDeptAgentPerson}"/> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">招标管理部门意见</td>
        <td class="td_edit" colspan="3">		
			<s:textarea  name="bid.zhaobiaoDeptComments" value="%{bid.zhaobiaoDeptComments}"  cols="80" rows="3"/> 	<br>
			备选内容:<input type="button" class="button" value="资质齐全，同意招标！" onclick="setDefaultApproveComment()"> <br>
			备选内容:<input type="button" class="button" value="资质不符合要求，不得招标！" onclick="setDefaultRejectComment()"> 
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">招标管理部门审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="zbdeptApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">负责人</td>
        <td class="td_edit" colspan="3">		
			<s:textfield  name="bid.zhaobiaoDeptResponsiblePerson" value="%{bid.zhaobiaoDeptResponsiblePerson}"  /> 	 
        </td>
    </tr>
    <%--  <tr>
        <td height="24" align="right" class="td_lable">纪检委审核人</td>
        <td class="td_edit" colspan="3">		
			<html:person idName="useridStr"  nameName="userNameStr" height="50"/>		
        </td>
    </tr> --%>
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
      <input type="button" onclick="approve()"  value="同意招标">
      <input type="button" onclick="reject()"  value="不同意招标">
       <input type="button" onclick="location.href='${root}/workflow/onbid.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>