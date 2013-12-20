<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<script type="text/javascript">
function approve() {
	var frm =document.getElementById('bidForm');
	if(window.confirm("确实同意招标?")){
		frm.action="${root}/workflow/onbid4_jjwapprove!approve.do";  
	 	frm.submit();
	}
}
function reject() {
	var frm =document.getElementById('bidForm');
	if(window.confirm("确实不同意招标?")){
		frm.action="${root}/workflow/onbid4_jjwapprove!reject.do";  
	 	frm.submit();
	}
}
</script>
</html:pageHead>
<body>
<html:pagetitle title="项目招标->纪律监察委员会审核"/>

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
        	<s:property value='#st.index+1'/>.<s:property value="name"/><br>
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
        <td height="24" align="right" class="td_lable">竞标时间</td>
        <td class="td_edit" colspan="3">		
			<s:textfield  name="bid.bidDate" value="%{bid.bidDate}"  cssClass="date" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">招竞标形式</td>
        <td class="td_edit">		
			<s:property   value="%{bid.bidType}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">经办人</td>
        <td class="td_edit">		
			<s:property  value="%{bid.zhaobiaoDeptAgentPerson}"/> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">招标管理部门意见</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.zhaobiaoDeptComments}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">负责人</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{bid.zhaobiaoDeptResponsiblePerson}"  /> 	 
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
    	<td height="24" align="right" class="td_lable">纪检监察机关审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="jjwApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">纪检监察机关意见</td>
        <td class="td_edit" colspan="3">		
			<s:textfield name="bid.jjwDeptComments"  value="%{bid.jjwDeptComments}" size="80"/> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">负责人</td>
        <td class="td_edit" colspan="3">		
			<s:textfield name="bid.jjwDeptResponsiblePerson"  value="%{bid.jjwDeptResponsiblePerson}"  /> 	 
        </td>
    </tr>
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